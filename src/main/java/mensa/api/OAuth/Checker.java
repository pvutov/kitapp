package mensa.api.OAuth;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

/**
 * @author http://android-developers.blogspot.de/2013/01/verifying-back-end-calls-from-android.html
 */
public class Checker {

    private final String APP_ID =  "785844054287-moelp1283p15ofou728h6vjg7pvgn1s6.apps.googleusercontent.com";
    private final String AUDIENCE = "785844054287-7hge652kf27md81acog9vg1u0nk9so83.apps.googleusercontent.com";
    private final GoogleIdTokenVerifier VERIFIER;
    private final GsonFactory JSON_FACTORY;
    private String mProblem = "Verification failed. (Time-out?)";
    private static Checker checker = new Checker();

    public Checker() {
        NetHttpTransport transport = new NetHttpTransport();
        JSON_FACTORY = new GsonFactory();
        VERIFIER = new GoogleIdTokenVerifier(transport, JSON_FACTORY);
    }

    public GoogleIdToken.Payload check(String tokenString) {
        GoogleIdToken.Payload payload = null;
		System.out.println('h');
        try {
            GoogleIdToken token = GoogleIdToken.parse(JSON_FACTORY, tokenString);
            if (VERIFIER.verify(token)) {
        		System.out.println('t');
                GoogleIdToken.Payload tempPayload = token.getPayload();
                if (!tempPayload.getAudience().equals(AUDIENCE))
                    mProblem = "Audience mismatch";
                else if (APP_ID != tempPayload.getAuthorizedParty())
                    mProblem = "Client ID mismatch";
                else
                    payload = tempPayload;
            } else {
            	mProblem = "Token is not valid";
            }
        } catch (GeneralSecurityException e) {
            mProblem = "Security issue: " + e.getLocalizedMessage();
        } catch (IOException e) {
            mProblem = "Network problem: " + e.getLocalizedMessage();
        }
		System.out.println('h');
        return payload;
    }

    public String getProblem() {
        return mProblem;
    }
    
    public static int getUserid(String token) throws BadTokenException {
    	Payload payload = checker.check(token);
    	int userid;
    	
    	if (payload == null) {
    		System.out.println(checker.getProblem());
			throw new BadTokenException();
    	} else {
    		try {
    			userid = Integer.parseInt(payload.getSubject());
    		} catch (NumberFormatException e) {
    			System.out.println("Google has returned a non-number in the subject field; This shouldn't be happening!");
    			e.printStackTrace();
    			throw new BadTokenException();
    		}
    	}
    	
    	return userid;
    }
}