package com.wb_configurator.wb_report_bot.bot;

import com.wb_configurator.wb_report_bot.model.User;
import com.wb_configurator.wb_report_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ReportTelegramBot extends TelegramLongPollingBot {

    final String botName;
    final UserRepository userRepository;

    public ReportTelegramBot(@Value("${bot.token}") String botToken,
                             @Value("${bot.name}") String botName,
                             UserRepository userRepository) {
        super(botToken);
        this.botName = botName;
        this.userRepository = userRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            long chatId = message.getChatId();

            if ("/start".equals(messageText)) {
                registerUser(message);
                startCommand(chatId, message.getChat().getFirstName());
            } else {
                sendMessage(chatId, "Вы написали: \"" + messageText + "\"");
            }
        }
    }

    private void registerUser(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {
            User newUser = new User();
            newUser.setTelegramId(message.getChatId());
            newUser.setFirstName(message.getChat().getFirstName());
            newUser.setUserName(message.getChat().getUserName());

            userRepository.save(newUser);
            System.out.println("Зарегистрирован новый пользователь: " + newUser.getUserName());
        }
    }

    private void startCommand(long chatId, String userName) {
        String response = "Привет, " + userName + "! Я бот для отчетов WB. " +
                "Чтобы добавить свой API-ключ, используй команду /setkey [твой_ключ]";
        sendMessage(chatId, response);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Добавить логгирование
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}