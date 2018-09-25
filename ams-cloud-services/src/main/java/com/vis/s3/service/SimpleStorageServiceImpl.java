package com.vis.s3.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.vis.common.util.AWSProperties;
import com.vis.exception.UnableToAccessS3Exception;
import com.vis.messages.S3UploadMessage;
import com.vis.response.ServiceResponse;

/**
 * The Class FileUploadServiceImpl.
 */
@Component
public class SimpleStorageServiceImpl implements SimpleStorageService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Override
	public ServiceResponse<S3UploadMessage> upload(MultipartFile multipart) {
		try {
			File convFile  = constructFileFromInputStream(multipart.getInputStream(), multipart.getOriginalFilename());
			return upload(multipart.getInputStream(),multipart.getOriginalFilename());
		} catch (IllegalStateException | IOException e) {
			logger.debug("unable to upload file to s3", e);
		}
		return null;
	}

	@Override
	public ServiceResponse<S3UploadMessage> upload(InputStream inputStream, String uploadKey) {
		ServiceResponse<S3UploadMessage> serviceResponse = ServiceResponse.getInstance();
		
		try {
		String key = generateKey(uploadKey);
		ObjectMetadata metadata = generateMetaData("s3", "this is the info about attachment with timestamp");
		PutObjectRequest putObjectRequest = new PutObjectRequest(AWSProperties.S3_BUCKET_NAME, key,inputStream, metadata);
		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
		IOUtils.closeQuietly(inputStream);

		S3UploadMessage s3UploadResponse = new S3UploadMessage();
		String fileName = putObjectRequest.getKey();
		
		s3UploadResponse.setFileReference(key);
		serviceResponse.setResult(s3UploadResponse);
		serviceResponse.setStatusCode(200);
		serviceResponse.setServiceMessage("File Uploaded Successfully");
		serviceResponse.setSuccessful(Boolean.TRUE);

	} catch (AmazonClientException ase) {
		
		logger.error("AWS FILE UPLOAD FAILURE " + ase);
		ase.printStackTrace();
		serviceResponse.setResult(null);
		serviceResponse.setSuccessful(Boolean.FALSE);
		
	}
		return serviceResponse;
	}

	@Override
	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(AWSProperties.S3_BUCKET_NAME));
		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
		return s3ObjectSummaries;
	}

	public ServiceResponse<Boolean> delete(String reference) {
		ServiceResponse<Boolean> response = ServiceResponse.getInstance();
		try {
			amazonS3Client.deleteObject(AWSProperties.S3_BUCKET_NAME, reference);
			response.setResult(Boolean.TRUE);
		} catch (AmazonServiceException e) {
			response.setResult(Boolean.FALSE);
		} catch (AmazonClientException e) {
			response.setResult(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Boolean deleteFile(String reference) throws UnableToAccessS3Exception {
		logger.info(" Inside delete method of FileUploadServiceImpl  ");
		Boolean result = false;
		try {
			if (amazonS3Client.doesObjectExist(AWSProperties.getProperty(AWSProperties.S3_BUCKET_NAME), reference)) {
				amazonS3Client.deleteObject(AWSProperties.getProperty(AWSProperties.S3_BUCKET_NAME), reference);
				result = true;
			} else {
				throw new UnableToAccessS3Exception();
			}
		} catch (AmazonClientException e) {
			logger.error("Error occurred while deleting file. " + e.getMessage(), e);
			throw new UnableToAccessS3Exception(e.toString());
		}
		return result;
	}

	public ServiceResponse<Boolean> moveFile(String fileName, String source, String destination) {
		@SuppressWarnings("unchecked")
		ServiceResponse<Boolean> serviceResponse = ServiceResponse.getInstance();
		amazonS3Client.getObject(source, fileName);
		return serviceResponse;
	}

	@Override
	public ServiceResponse<InputStream> download(String referenceID) {
		ServiceResponse<InputStream> serviceResponse = ServiceResponse.getInstance();

		S3Object s3object = amazonS3Client.getObject(AWSProperties.S3_BUCKET_NAME, referenceID);
		InputStream stream = s3object.getObjectContent();
		if (stream != null) {
			serviceResponse.setResult(stream);
			serviceResponse.setSuccessful(Boolean.TRUE);
		} else {
			serviceResponse.setResult(null);
			serviceResponse.setSuccessful(Boolean.FALSE);
		}

		return serviceResponse;
	}

	/**
	 * Download.
	 *
	 * @param fileName
	 *            the file name
	 * @return the service response
	 * @throws UnableToAccessS3Exception
	 *             the unable to access S 3 exception
	 */
	@Override
	public File downloadFile(String referenceNumber) throws UnableToAccessS3Exception {
		S3Object s3object = amazonS3Client.getObject(AWSProperties.getProperty(AWSProperties.S3_BUCKET_NAME), referenceNumber);
		InputStream stream = s3object.getObjectContent();
		File file = constructFileFromInputStream(stream, s3object.getKey());
		if (file != null) {
			return file;
		} else {
			throw new UnableToAccessS3Exception();
		}
	}

	private File constructFileFromInputStream(InputStream inputStream, String fileName) {
		File file = null;
		try {
			file = new File(fileName);
			FileUtils.copyInputStreamToFile(inputStream, file);
		} catch (IOException e) {
			logger.error("IOException raised while copying data into file. " + e.getMessage(), e);
		}
		return file;
	}

	/**
	 * Generate key.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	private String generateKey(String key) {
		int pos = key.lastIndexOf('.');
		return StringUtils.join(key.substring(0, pos), "_", System.currentTimeMillis());
	}

	/**
	 * Generate meta data.
	 *
	 * @param metaKey
	 *            the meta key
	 * @param metaValue
	 *            the meta value
	 * @return the object metadata
	 */
	private ObjectMetadata generateMetaData(String metaKey, String metaValue) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.addUserMetadata(metaKey, metaValue);
		return metadata;
	}

}
