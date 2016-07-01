/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.mail;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.jk.util.JKConversionUtil;

/**
 * The Class MailInfo.
 *
 * @author Jalal Kiswani
 */
public class MailInfo {
	String host = System.getProperty("jk-mail-host", "smtp.gmail.com");
	boolean requiresAuthentication = JKConversionUtil.toBoolean(System.getProperty("jk-mail-host-auth", "true"));
	boolean secured = JKConversionUtil.toBoolean(System.getProperty("jk-mail-secured", "true"));
	private String username = System.getProperty("jk-mail-user");
	private String password = System.getProperty("jk-mail-password");
	private int smtpPort = JKConversionUtil.toInteger(System.getProperty("jk-mail-smtp-port", "587"));

	String from;
	String to;
	String subject;
	String msg;
	ArrayList<Attachment> attachements = new ArrayList<Attachment>();

	/**
	 * Adds the attachment.
	 *
	 * @param attachment
	 *            the attachment
	 */
	public void addAttachment(final Attachment attachment) {
		this.attachements.add(attachment);
	}

	/**
	 * Fill email.
	 *
	 * @param email
	 *            the email
	 * @throws EmailException
	 *             the email exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void fillEmail(final MultiPartEmail email) throws EmailException, IOException {
		email.setHostName(getHost());
		email.setSmtpPort(getSmtpPort());
		
		email.addTo(getTo());
		email.setFrom(getFrom());
		email.setSubject(getSubject());
		email.setMsg(getMsg());
		email.setSSLOnConnect(isSecured());
		if (isRequiresAuthentication()) {
			email.setAuthentication(getUsername(), getPassword());
		}
		for (int i = 0; i < this.attachements.size(); i++) {
			final Attachment attachment = this.attachements.get(i);
			final ByteArrayDataSource ds = new ByteArrayDataSource(attachment.getData(), attachment.getMimeType());
			email.attach(ds, attachment.getName(), attachment.getDescription());
		}
	}

	/**
	 * Gets the smtp port.
	 *
	 * @return the smtp port
	 */
	public int getSmtpPort() {
		return smtpPort;
	}

	/**
	 * Sets the smtp port.
	 *
	 * @param smtpPort
	 *            the new smtp port
	 */
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * Checks if is requires authentication.
	 *
	 * @return true, if is requires authentication
	 */
	public boolean isRequiresAuthentication() {
		return requiresAuthentication;
	}

	/**
	 * Sets the requires authentication.
	 *
	 * @param requiresAuthentication
	 *            the new requires authentication
	 */
	public void setRequiresAuthentication(boolean requiresAuthentication) {
		this.requiresAuthentication = requiresAuthentication;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	private String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return this.from;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return this.to;
	}

	/**
	 * Sets the from.
	 *
	 * @param from
	 *            the from to set
	 */
	public void setFrom(final String from) {
		this.from = from;
	}

	/**
	 * Sets the host.
	 *
	 * @param host
	 *            the host to set
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(final String msg) {
		this.msg = msg;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	/**
	 * Sets the to.
	 *
	 * @param to
	 *            the to to set
	 */
	public void setTo(final String to) {
		this.to = to;
	}

	/**
	 * Checks if is secured.
	 *
	 * @return true, if is secured
	 */
	public boolean isSecured() {
		return secured;
	}

	/**
	 * Sets the secured.
	 *
	 * @param secured
	 *            the new secured
	 */
	public void setSecured(boolean secured) {
		this.secured = secured;
	}
	
	
}
