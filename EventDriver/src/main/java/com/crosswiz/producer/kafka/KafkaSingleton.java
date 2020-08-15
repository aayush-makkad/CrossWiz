package com.crosswiz.producer.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import com.crosswiz.common.errors.KAFKA_ERRORS;

public class KafkaSingleton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8492993918230304933L;

	private static final String KAFKA_PROPERTY_FILE = "kafka-producer.properties";
	private final static KafkaSingleton KAFKA_WRAPPER_INSTANCE = new KafkaSingleton();
	private static Producer<String, String> producer;
	private static final Logger log = Logger.getLogger(KafkaSingleton.class);

	private static final Properties _properties = new Properties();

	public KafkaSingleton getKafkaWrapper() {
		return KAFKA_WRAPPER_INSTANCE;
	}

	private Object readResolve() throws ObjectStreamException {
		return KAFKA_WRAPPER_INSTANCE;
	}

	private KafkaSingleton() {
		try {
			bootstrap();
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.warn(KAFKA_ERRORS.KAFKA_PROPERTY_READ_FAILURE);
		}
	}

	private void bootstrap() throws IOException {

		InputStream _propertyFileInputStream = KafkaSingleton.class.getResourceAsStream(KAFKA_PROPERTY_FILE);
		_properties.load(_propertyFileInputStream);
		producer = new KafkaProducer<>(_properties);
	}

	public void send(String key, String message) {
		producer.send(new ProducerRecord<String, String>(_properties.getProperty("topic"), key, message));
	}

}
