/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.giveawaybot.commands;

import com.jagrosh.giveawaybot.Bot;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class EvalCommand extends Command {

    private final Bot bot;
    public EvalCommand(Bot bot) {
        this.bot = bot;
        name = "eval";
        help = "evaluates Nashorn code";
        arguments = "<code>";
        ownerCommand = true;
        guildOnly = false;
        category = new Category("Owner");
    }
    
    @Override
    protected void execute(CommandEvent event) {
        ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
        se.put("event", event);
        se.put("jda", event.getJDA());
        se.put("guild", event.getGuild());
        se.put("channel", event.getChannel());
        se.put("bot", bot);
        try
        {
            event.reply(event.getClient().getSuccess()+" Evaluated Successfully:\n```\n"+se.eval(event.getArgs())+" ```");
        } 
        catch(Exception e)
        {
            event.reply(event.getClient().getError()+" An exception was thrown:\n```\n"+e+" ```");
        }
    }
    
}
