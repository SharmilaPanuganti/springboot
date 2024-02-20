package cgg.smartcontact.smartcontactmanager.entities;

import lombok.Data;

@Data
public class Message {

  private String message;
  private String cssClass;

  public Message(String message, String cssClass) {
    this.message = message;
    this.cssClass = cssClass;
  }
}
