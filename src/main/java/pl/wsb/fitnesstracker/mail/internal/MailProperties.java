package pl.wsb.fitnesstracker.mail.internal;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
class MailProperties {

    /**
     * Email address that the email should be sent from.
     */
    private String from;

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
}