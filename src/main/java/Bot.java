import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
  public static void main(String[] args) {
    ApiContextInitializer.init();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    try {
      telegramBotsApi.registerBot(new Bot());
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }

  public void onUpdateReceived(Update update) {
    System.out.println(update.getMessage().getText());
    Message message = update.getMessage();
    if (message != null && message.hasText()) {
      switch (message.getText()) {
        case "/help":
          sendMsg(message, "lllll");
          break;
        case "/settings":
          sendMsg(message, "2222222");
        default:
          System.out.println("000");
      }
    }
  }

  public void setButtons(SendMessage sendMessage){
    ReplyKeyboardMarkup replyKeyboardMarkup =  new ReplyKeyboardMarkup();
    sendMessage.setReplyMarkup(replyKeyboardMarkup);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);
    List<KeyboardRow> rows = new ArrayList<>();
    KeyboardRow keyboard = new KeyboardRow();
    keyboard.add(new KeyboardButton("/help"));
    keyboard.add(new KeyboardButton("/setting"));
    rows.add(keyboard);
    replyKeyboardMarkup.setKeyboard(rows);
  }

  public void sendMsg(Message m,String text){
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(m.getChatId());
    sendMessage.setReplyMarkup(m.getReplyMarkup());
    sendMessage.setText(text);
    try {
      setButtons(sendMessage);
      execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


  public String getBotUsername() {
    return "teste23211rbot";
  }

  public String getBotToken() {
    return "943439098:AAGYpDi8JryTtki1MloebBmae16xtSnlqXc";
  }
}
