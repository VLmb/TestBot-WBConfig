package com.wb_configurator.wb_report_bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ReportTelegramBot extends TelegramLongPollingBot {

    final String botName;

    public ReportTelegramBot(@Value("${bot.token}") String botToken,
                             @Value("${bot.name}") String botName) {
        super(botToken);
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(">>> ПОЛУЧЕНО НОВОЕ СООБЩЕНИЕ!");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String responseText;
            if (messageText.equals("/start")) {
                responseText = "Привет! Я бот для создания отчетов по Wildberries. " +
                        "Пока я просто умею отвечать на сообщения.";
            } else {
                responseText = "Вы написали: \"" + messageText + "\"";
            }

            SendMessage messageToSend = new SendMessage();
            messageToSend.setChatId(chatId);
            messageToSend.setText(responseText);

            try {
                execute(messageToSend);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}