
package com.vis.s3.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.vis.exception.UnableToAccessS3Exception;
import com.vis.messages.S3UploadMessage;
import com.vis.response.ServiceResponse;

/**
 * The Interface FileUploadService.
 */
@Component
public interface SimpleStorageService {

	public ServiceResponse<S3UploadMessage> upload(InputStream inputStream, String uploadKey);

	public ServiceResponse<S3UploadMessage> upload(MultipartFile multipart);

	public ServiceResponse<Boolean> delete(String reference);

	public ServiceResponse<Boolean> moveFile(String fileName, String source, String destination);

	public ServiceResponse<InputStream> download(String referenceID);

	public List<S3ObjectSummary> list();

	public Boolean deleteFile(String reference) throws UnableToAccessS3Exception;

	public File downloadFile(String referenceNumber) throws UnableToAccessS3Exception;

}
