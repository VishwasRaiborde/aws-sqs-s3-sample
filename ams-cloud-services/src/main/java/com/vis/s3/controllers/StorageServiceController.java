package com.vis.s3.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.vis.exception.ServiceException;
import com.vis.messages.S3UploadMessage;
import com.vis.response.ServiceResponse;
import com.vis.s3.service.SimpleStorageService;

/**
 * The Class AccountController.
 *
 * @Desc AccountController routes request and response
 */

@RestController
public class StorageServiceController {

	/** The upload service. */
	@Autowired
	private SimpleStorageService simpleStorageService;

	/**
	 * Order.
	 *
	 * @return the service response
	 * @throws ServiceException
	 *             the service exception
	 */
	@ApiOperation(value = "Multipart file upload", notes = "Upload a file to an ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "") })
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ServiceResponse<S3UploadMessage> upload(@RequestParam(name = "file") MultipartFile file)
			throws ServiceException {
		ServiceResponse<S3UploadMessage> serviceResponse = simpleStorageService.upload(file);
		return serviceResponse;

	}

	/**
	 * Delete.
	 *
	 * @param fileReferece
	 *            the file referece
	 * @return the service response
	 * @throws ServiceExceptionResponse
	 *             the service exception
	 */
	@ApiOperation(value = "file download", notes = "download a file")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "") })
	@RequestMapping(value = "/download/{referenceid}", method = RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void download(@PathVariable("referenceid") String referenceid,HttpServletResponse response) {
	    try {
	    	InputStream is = simpleStorageService.download(referenceid).getResult();
	        org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        response.flushBuffer();
	      //  return is;
	      } catch (IOException ex) {
	        throw new RuntimeException("IOError writing file to output stream");
	      }
	}
	/**
	 * Move file.
	 *
	 * @param sourceLocation
	 *            the source location
	 * @param destinationLocation
	 *            the destination location
	 * @return the service response
	 * @throws ServiceException
	 *             the service exception
	 */
	@RequestMapping(value = "/move/{referenceid}", method = RequestMethod.PUT)
	public ServiceResponse<Boolean> moveFile(@PathVariable("referenceid") String referenceid, @RequestParam("source") String sourcePath,
			@RequestParam("destinationPath") String destinationPath) throws ServiceException {
		return simpleStorageService.moveFile(referenceid, sourcePath, destinationPath);
	}

	@RequestMapping(value = "/delete/{referenceid}", method = RequestMethod.DELETE)
	public ServiceResponse<Boolean> delete(@PathVariable("referenceid") String referenceid) throws ServiceException {
		return simpleStorageService.delete(referenceid);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.DELETE)
	public List<S3ObjectSummary> delete() throws ServiceException {
		return simpleStorageService.list();
	}

}
