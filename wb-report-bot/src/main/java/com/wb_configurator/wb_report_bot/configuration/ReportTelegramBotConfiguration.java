package com.wb_configurator.wb_report_bot.configuration;

import com.wb_configurator.wb_report_bot.bot.ReportTelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ReportTelegramBotConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(ReportTelegramBot reportTelegramBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(reportTelegramBot);
        return api;
    }
}
