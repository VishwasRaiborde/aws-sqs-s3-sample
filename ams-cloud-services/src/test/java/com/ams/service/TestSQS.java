package com.ams.service;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class TestSQS {

	public static void main(String[] args) {
		AmazonSQSClient sqs=new AmazonSQSClient(new SystemPropertiesCredentialsProvider());
		Region apSouthEast1 = Region.getRegion(Regions.US_EAST_1);
        sqs.setRegion(apSouthEast1);
		System.out.println("Listing all queues in your account.\n");
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }
	}

}
