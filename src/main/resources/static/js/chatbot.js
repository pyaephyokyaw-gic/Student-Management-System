// Chatbot functionality
class Chatbot {
    constructor() {
        this.isOpen = false;
        this.isTyping = false;
        this.init();
    }

    init() {
        // Create chatbot HTML structure
        this.createChatbotUI();
        
        // Add event listeners
        this.addEventListeners();
        
        // Add welcome message
        this.addBotMessage("Hello! I'm your AI assistant. How can I help you with the Student Management System today?");
    }

    createChatbotUI() {
        const chatbotHTML = `
            <div class="chatbot-container">
                <button class="chatbot-toggle" id="chatbotToggle">
                    💬
                </button>
                
                <div class="chatbot-window" id="chatbotWindow">
                    <div class="chatbot-header">
                        <h3>AI Assistant</h3>
                        <button class="chatbot-close" id="chatbotClose">&times;</button>
                    </div>
                    
                    <div class="chatbot-messages" id="chatbotMessages">
                        <!-- Messages will be added here -->
                    </div>
                    
                    <div class="chatbot-input-area">
                        <input 
                            type="text" 
                            class="chatbot-input" 
                            id="chatbotInput" 
                            placeholder="Type your message..."
                            autocomplete="off"
                        />
                        <button class="chatbot-send" id="chatbotSend">
                            ➤
                        </button>
                    </div>
                </div>
            </div>
        `;
        
        document.body.insertAdjacentHTML('beforeend', chatbotHTML);
    }

    addEventListeners() {
        const toggle = document.getElementById('chatbotToggle');
        const close = document.getElementById('chatbotClose');
        const send = document.getElementById('chatbotSend');
        const input = document.getElementById('chatbotInput');

        toggle.addEventListener('click', () => this.toggleChatbot());
        close.addEventListener('click', () => this.toggleChatbot());
        send.addEventListener('click', () => this.sendMessage());
        
        input.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.sendMessage();
            }
        });
    }

    toggleChatbot() {
        const window = document.getElementById('chatbotWindow');
        this.isOpen = !this.isOpen;
        
        if (this.isOpen) {
            window.classList.add('active');
            document.getElementById('chatbotInput').focus();
        } else {
            window.classList.remove('active');
        }
    }

    async sendMessage() {
        const input = document.getElementById('chatbotInput');
        const message = input.value.trim();
        
        if (!message || this.isTyping) return;
        
        // Add user message to chat
        this.addUserMessage(message);
        
        // Clear input
        input.value = '';
        
        // Show typing indicator
        this.showTypingIndicator();
        
        // Disable send button
        this.setSendButtonState(false);
        
        try {
            // Send message to backend
            const response = await fetch('/api/chatbot/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ message: message })
            });
            
            const data = await response.json();
            
            // Remove typing indicator
            this.hideTypingIndicator();
            
            // Add bot response
            if (data.success) {
                this.addBotMessage(data.response);
            } else {
                this.addBotMessage(data.error || 'Sorry, something went wrong. Please try again.');
            }
            
        } catch (error) {
            console.error('Error sending message:', error);
            this.hideTypingIndicator();
            this.addBotMessage('Sorry, I\'m having trouble connecting. Please check your internet connection and try again.');
        } finally {
            // Enable send button
            this.setSendButtonState(true);
        }
    }

    addUserMessage(message) {
        const messagesContainer = document.getElementById('chatbotMessages');
        const messageHTML = `
            <div class="chat-message user">
                <div class="message-content">${this.escapeHtml(message)}</div>
            </div>
        `;
        messagesContainer.insertAdjacentHTML('beforeend', messageHTML);
        this.scrollToBottom();
    }

    addBotMessage(message) {
        const messagesContainer = document.getElementById('chatbotMessages');
        const messageHTML = `
            <div class="chat-message bot">
                <div class="message-content">${this.escapeHtml(message)}</div>
            </div>
        `;
        messagesContainer.insertAdjacentHTML('beforeend', messageHTML);
        this.scrollToBottom();
    }

    showTypingIndicator() {
        this.isTyping = true;
        const messagesContainer = document.getElementById('chatbotMessages');
        const typingHTML = `
            <div class="chat-message bot" id="typingIndicator">
                <div class="message-content">
                    <div class="typing-indicator">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                </div>
            </div>
        `;
        messagesContainer.insertAdjacentHTML('beforeend', typingHTML);
        this.scrollToBottom();
    }

    hideTypingIndicator() {
        this.isTyping = false;
        const indicator = document.getElementById('typingIndicator');
        if (indicator) {
            indicator.remove();
        }
    }

    setSendButtonState(enabled) {
        const sendButton = document.getElementById('chatbotSend');
        sendButton.disabled = !enabled;
    }

    scrollToBottom() {
        const messagesContainer = document.getElementById('chatbotMessages');
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }
}

// Initialize chatbot when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    new Chatbot();
});
