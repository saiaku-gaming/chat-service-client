package com.valhallagame.chatserviceserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import com.valhallagame.chatserviceserver.model.ChatMessage;
import com.valhallagame.chatserviceserver.model.ChatType;
import com.valhallagame.common.rabbitmq.RabbitMQRouting.Party;
import com.valhallagame.common.rabbitmq.RabbitMQRouting.Person;

@Service
public class ChatService {

	private static final Map<String, List<ChatMessage>> messagesMap = new HashMap<>();

	public void sendMessageToParty(Person sender, String message) {
		Party party = sender.getParty();

		if (party == null) {
			return;
		}

		String senderCharacterName = sender.getSelectedCharacter() != null ? sender.getSelectedCharacter().getName()
				: "";

		for (Person member : party.getPartyMembers()) {
			addMessage(member,
					new ChatMessage(sender.getDisplayUsername(), senderCharacterName, message, ChatType.PARTY));

			notificationService.addNotifications(NotificationType.CHATCHANGE, "party message", member);
		}
	}

	public void sendWhisperToPerson(String senderUsername, String message, String receiverUsername) {
		String senderCharacterName = senderUsername.getSelectedCharacter() != null
				? senderUsername.getSelectedCharacter().getName() : "";

		addMessage(receiverUsername,
				new ChatMessage(senderUsername.getDisplayUsername(), senderCharacterName, message, ChatType.WHISPER));

		notificationService.addNotifications(NotificationType.CHATCHANGE, "whisper message", receiverUsername);
	}

	public void sendSystemToPerson(String message, Person receiver) {
		addMessage(receiver, new ChatMessage("", "", message, ChatType.SYSTEM));
		notificationService.addNotifications(NotificationType.CHATCHANGE, "system message", receiver);
	}

	public void sendMessageToGeneral(Person sender, String message) {
		String senderCharacterName = sender.getSelectedCharacter() != null ? sender.getSelectedCharacter().getName()
				: "";

		for (Person person : personService.getAllOnlinePersons()) {
			addMessage(person,
					new ChatMessage(sender.getDisplayUsername(), senderCharacterName, message, ChatType.GENERAL));

			notificationService.addNotifications(NotificationType.CHATCHANGE, "general message", person);
		}
	}

	public void sendMessageToInstance(Person sender, String message) {
		String senderCharacterName = sender.getSelectedCharacter() != null ? sender.getSelectedCharacter().getName()
				: "";

		if (sender.getInstance() == null) {
			return;
		}

		for (Person person : personService.getAllPersonsInInstance(sender.getInstance())) {
			addMessage(person,
					new ChatMessage(sender.getDisplayUsername(), senderCharacterName, message, ChatType.INSTANCE));

			notificationService.addNotifications(NotificationType.CHATCHANGE, "instance message", person);
		}
	}

	public void clearMessagesForPerson(Person person) {
		List<ChatMessage> messages = messagesMap.get(person.getId());
		if (messages != null) {
			messages.clear();
		}
	}

	public List<ChatMessage> getChatMessagesForPerson(Person person) {
		List<ChatMessage> messages = messagesMap.get(person.getId());
		return messages == null ? Lists.emptyList() : messages;
	}

	public List<ChatMessage> getAndClearChatMessages(Person person) {
		List<ChatMessage> messages = new ArrayList<>(getChatMessagesForPerson(person));
		clearMessagesForPerson(person);

		return messages;
	}

	private void addMessage(String receiverUsername, ChatMessage message) {
		List<ChatMessage> messages = messagesMap.computeIfAbsent(receiverUsername, k -> new ArrayList<>());

		messages.add(message);
	}
}
