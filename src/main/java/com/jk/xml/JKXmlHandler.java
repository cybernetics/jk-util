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
package com.jk.xml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jk.exceptions.JKException;

public class JKXmlHandler {

	private static JKXmlHandler instance = new JKXmlHandler();

	public static JKXmlHandler getInstance() {
		return instance;
	}

	/*
	 * 
	 */
	public <T> T parse(InputStream in, Class<?>... clas) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clas);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			T t = (T) jaxbUnmarshaller.unmarshal(in);
			return t;
		} catch (JAXBException e) {
			throw new JKException(e);
		}
	}

	/**
	 * 
	 * @param object
	 * @param out
	 */
	public void toXml(Object object, OutputStream out, Class<?>... clas) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clas);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, out);
		} catch (JAXBException e) {
			throw new JKException(e);
		}
	}

	public String toXml(Object obj) {
		return toXml(obj, obj.getClass());
	}

	public String toXml(Object obj, Class<?>... clas) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		toXml(obj, out, clas);
		return out.toString();
	}
}
