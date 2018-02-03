package backend.agent;

import org.eclipse.jetty.io.ssl.ALPNProcessor;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class Agent {
    private static final String accessToken = "NDA5MzUzMjUwMTQ0Mzg3MDgy.DVdfCA.HxIptHwESf_r_mEg1Xc_KkKyoVc";
    private static final String botUserName = "Webjumper#0576";
    public static IDiscordClient createClient(String token, boolean login) {
        ClientBuilder cb = new ClientBuilder();
        cb.withToken(token);
        try {
            if (login) {
                return cb.login(); // Creates the client instance and logs the client in
            } else {
                return cb.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }


}
