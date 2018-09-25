package com.ams.service;

import junit.framework.TestCase;

import org.junit.Test;

public class MessageParserTest extends TestCase {
	
	
	@Test
	public void testGenericMessageOrder() {
		
	}
	/*

	

	@Test
	public void testGenericMessageOrder() {

		OrderMessage order = new OrderMessage();
		order.setCC_TYPE("CC_TYPE");
		order.setACTIVE_TRACK_OUTPUT("ACTIVE_TRACK_OUTPUT");
		order.setBILL_ATTN("bILL_ATTN");
		order.setCC_EXP_DATE("cC_EXP_DATE");
		order.setCC_NAME("cC_NAME");
		order.setCC_NUMBER("cC_NUMBER");
		order.setCC_SECURITY_CODE("cC_SECURITY_CODE");
		order.setCOUNTRY_CODE("cOUNTRY_CODE");
		order.setEBS_BILL_ADDRESS_ID("eBS_BILL_ADDRESS_ID");
		order.setEBS_BILL_SITE_NUMBER("eBS_BILL_SITE_NUMBER");
		order.setEBS_CUSTOMER_ID("eBS_CUSTOMER_ID");
		order.setEBS_CUSTOMER_NAME("eBS_CUSTOMER_NAME");
		order.setEBS_CUSTOMER_NUMBER("eBS_CUSTOMER_NUMBER");
		order.setEBS_SHIP_ADDRESS_ID("eBS_SHIP_ADDRESS_ID");
		order.setEBS_SHIP_SITE_NUMBER("eBS_SHIP_SITE_NUMBER");
		order.setEC_ORDER_ID(123123);
		order.setEC_ORDER_NUMBER("eC_ORDER_NUMBER");
		order.setEC_ORDER_SOURCE("eC_ORDER_SOURCE");
		order.setEC_ORDER_STATUS_CODE("eC_ORDER_STATUS_CODE");
		order.setEC_ORDER_SUBMIT_MSG("eC_ORDER_SUBMIT_MSG");
		order.setEC_ORDER_TYPE("eC_ORDER_TYPE");
		order.setOPERATING_UNIT("oPERATING_UNIT");
		order.setORG_ID("oRG_ID");
		order.setPO_NUMBER("pO_NUMBER");
		order.setPO_REFERENCE("pO_REFERENCE");
		order.setPRICE_LIST("pRICE_LIST");
		order.setSHIP_ATTN("sHIP_ATTN");
		order.setSHIP_METHOD_CODE("sHIP_METHOD_CODE");
		order.setSUB_TOTAL("sUB_TOTAL");
		order.setTAX_EXEMPTION_FLG("tAX_EXEMPTION_FLG");
		order.setTOTAL_PRICE("tOTAL_PRICE");
		
		order.setUserId("freerick");
		order.setChannel("CHANNER");
		order.setMessageType(SQSMessageType.ORDER);

		MessageParser messageParser = new MessageParser();
		String jsonAsString = messageParser.parseObjectToJson(order);
		System.out.println(jsonAsString.toString());
		OrderMessage OrderMessage = (com.ams.messages.OrderMessage) messageParser.parseJsonToObject(jsonAsString,OrderMessage.class);
		System.out.println(OrderMessage.toString());

	}
	
	@Test
	public void testGenericMessageEmail() {

		EmailMessage email = new EmailMessage();
		email.setUserId("freerick");
		email.setChannel("CHANNER");
		email.setMessageType(SQSMessageType.EMAIL);
		email.setEmailID("vishwas.raiborde@beckmain.com");

		MessageParser messageParser = new MessageParser();
		String jsonAsString = messageParser.parseObjectToJson(email);
		System.out.println(jsonAsString.toString());
		EmailMessage emailMessage = (EmailMessage) messageParser.parseJsonToObject(jsonAsString,EmailMessage.class);
		System.out.println(emailMessage.toString());

	}

*/}
