package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForWSPMessage;
import com.squareup.okhttp.*;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WhatsAppMessageSenderService {


    private final String userAccessToken = "EAAE6LNGxWjMBAH9aBRAIrJoE2IThlHCIGDOczQ4qFIfCD3B3ZC772KXNpttROUHXIYlDzUODoF8ryGZC3GEIy06fWH73s9ZBzXGKjAkgpKW8hrDvPZBFOFFQdzn9pfiT9iR4bYtMJ20fxPHbnrCbSm4Y4LDZBd6ZBHPPUfPIYJLrn82xON7DOiVl8pZA5cqNTBjmxJFsuZBv30FSZCZA7qbsAa";

    private final String whatsappID = "110610228360808";

    private final String rooturl = "https://graph.facebook.com/v13.0/";

    public String sendBookingConfirmationMessage(BookingDetailsForWSPMessage bookingDetails) throws IOException {
        {

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");


            String json = "{\n    \"messaging_product\": \"whatsapp\",\n    \"to\": \"--toSend--\",\n    \"type\": \"template\",\n    \"template\": {\n        \"name\": \"booking_confirmation\",\n        \"language\": {\n            \"code\": \"es_AR\"\n        },\n        \"components\": [\n            {\n                \"type\": \"body\",\n                \"parameters\": [\n                    {\n                        \"type\": \"text\",\n                        \"text\": \" --location-- \"\n                    },\n                    {\n                        \"type\": \"text\",\n                        \"text\": \" --BookingId-- \"\n                    },\n                    {\n                        \"type\": \"text\",\n                        \"text\": \" --ProductName-- \"\n                    },\n                    {\n                        \"type\": \"date_time\",\n                        \"date_time\": {\n                            \"fallback_value\": \" --DateArrive-- \"\n                        }\n                    },\n                    {\n                        \"type\": \"date_time\",\n                        \"date_time\": {\n                            \"fallback_value\": \" --ETA-- \"\n                        }\n                    }\n                ]\n            }\n        ]\n    }\n}";

            StringBuilder sb = new StringBuilder();
            sb.append(json);
            sb.replace(sb.indexOf("--toSend--"), sb.indexOf("--toSend--") + "--toSend--".length(), "54"+bookingDetails.getUserPhone());
            sb.replace(sb.indexOf("--location--"), sb.indexOf("--location--") + "--location--".length(), bookingDetails.getLocationName());
            sb.replace(sb.indexOf("--BookingId--"), sb.indexOf("--BookingId--") + "--BookingId--".length(), "Bk-000" + bookingDetails.getId().toString());
            sb.replace(sb.indexOf("--ProductName--"), sb.indexOf("--ProductName--") + "--ProductName--".length(), bookingDetails.getProductName());
            sb.replace(sb.indexOf("--DateArrive--"), sb.indexOf("--DateArrive--") + "--DateArrive--".length(), bookingDetails.getStartDate());
            sb.replace(sb.indexOf("--ETA--"), sb.indexOf("--ETA--") + "--ETA--".length(), bookingDetails.getETA());

            RequestBody body = RequestBody.create(mediaType, sb.toString());

            //RequestBody body = RequestBody.create(mediaType, json);

            Request request = new Request.Builder()
                    .url(rooturl + whatsappID + "/messages")
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + userAccessToken)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        }

    }

}
