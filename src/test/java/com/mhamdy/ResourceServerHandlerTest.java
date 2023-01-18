package com.mhamdy;

import com.mhamdy.handler.ResourceServerHandler;
import com.mhamdy.handler.TokenHandler;
import com.mhamdy.model.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by: Mahmoud Hamdy on 18/01/2023
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ResourceServerHandlerTest {

    @Autowired
    TokenHandler tokenHandler;
    @Autowired
    ResourceServerHandler resourceServerHandler;

    /**
     * ofc will not pass due the fake Auth Server but this is the expected flow.
     */
    @Test
    public void testExpectedOAuth2Flow() {
        String token = tokenHandler.getAccessToken();
        CreditCard creditCard = new CreditCard("4536246923381325", "1/27", "257", "Elroy Wiers");
        resourceServerHandler.callResourceServer(token, creditCard);
    }
}
