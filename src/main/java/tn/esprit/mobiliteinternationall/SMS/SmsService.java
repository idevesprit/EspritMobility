package tn.esprit.mobiliteinternationall.SMS;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
@Component

public class SmsService {
    private final String ACCOUNT_SID ="AC3ed56db411933cc6a5bad98ee67878e3";

    private final String AUTH_TOKEN = "19f40569cf5e4211ff4671d4fb49a2e6";

    private final String FROM_NUMBER = "+12025090080";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
