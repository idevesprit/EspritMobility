package tn.esprit.mobiliteinternationall.services;

import com.sun.xml.bind.v2.runtime.unmarshaller.StructureLoader;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import tn.esprit.mobiliteinternationall.entites.SMS;

import java.net.URI;


@Service
public class SMSService {
        @Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
        private String ACCOUNT_SID;

    /*
    @Value("#{systemEnvironment['COMPONENT_PARAM_CORS'] ?: 'DEFAULT_VALUE'}")
private String COMPONENT_PARAM_CORS;
     */

        @Value("#{systemEnvironment['TWILIO_AUTH_TOKEN']}")
        private String AUTH_TOKEN;

        @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
        private String FROM_NUMBER;

        public void send(SMS sms) {
                //public void send(SMS sms) {
                     //   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                        // to number, from: always twilio snd a message
                       // Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                            //    .setStatusCallback(URI.create("http://677add1a.ngrok.io/smscallback"))
                        //        .create();
                       // System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction


                }

        public void receive(MultiValueMap<String, String> smscallback) {
        }
}
