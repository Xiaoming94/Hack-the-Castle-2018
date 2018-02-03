package backend.agent;

import org.eclipse.jetty.io.ssl.ALPNProcessor;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;

import backend.graph.PageNode;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Agent {
    private static final String accessToken = "NDA5MzUzMjUwMTQ0Mzg3MDgy.DVdfCA.HxIptHwESf_r_mEg1Xc_KkKyoVc";
    private static final String botUserName = "Webjumper#0576";
    private boolean login = false;
    private PageNode currentPageNode;
    private IDiscordClient client;

    public Agent(PageNode currentPageNode){
        this.currentPageNode = currentPageNode;
        client = this.createClient(accessToken,false);
    }

    public IDiscordClient createClient(String token, boolean login) {
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
    public boolean login(){
        if(!login) {
            this.client = createClient(accessToken, true);
            this.login = true;
        }
        return true;
    }

    public IDiscordClient getClient(boolean login){
        if (this.client == null){
            this.client = createClient(accessToken,login);
        }
        return client;
    }

    public void setCurrentPageNode(PageNode newPageNode){
        currentPageNode = newPageNode;
    }

    public void handle(MessageReceivedEvent event){
        IMessage message = event.getMessage();
        IChannel channel = message.getChannel();
        try {
            // Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
            new MessageBuilder(this.client).withChannel(channel).withContent(message.getContent()).build();
        } catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
            System.err.print("Sending messages too quickly!");
            e.printStackTrace();
        } catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
            System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
            e.printStackTrace();
        } catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
            System.err.print("Missing permissions for channel!");
            e.printStackTrace();
        }
    }


}
