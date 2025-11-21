# AI Chatbot Integration with Google Gemini

This document explains how to set up and use the AI chatbot assistant powered by Google Gemini in the Student Management System.

## Features

✨ **AI-Powered Assistant**: Integrated with Google Gemini 1.5 Flash (free tier)
💬 **Real-time Chat Interface**: Beautiful, modern chat UI with typing indicators
🎨 **Responsive Design**: Works seamlessly on desktop and mobile devices
🔒 **Secure API Integration**: Backend REST API for handling chat requests
📚 **Context-Aware**: Chatbot understands the Student Management System context

## Setup Instructions

### 1. Get Your Free Google Gemini API Key

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account
3. Click "Create API Key" or "Get API Key"
4. Copy the generated API key

### 2. Configure the API Key

Open the `application.properties` file located at:
```
src/main/resources/application.properties
```

Replace `YOUR_GEMINI_API_KEY_HERE` with your actual API key:
```properties
gemini.api.key=YOUR_ACTUAL_API_KEY_HERE
gemini.model=gemini-1.5-flash
```

**Important**: Never commit your API key to version control. Consider using environment variables in production.

### 3. Install Dependencies

Run Maven to download the required dependencies:
```bash
./mvnw clean install
```

Or if using Windows:
```cmd
mvnw.cmd clean install
```

### 4. Run the Application

Start the Spring Boot application:
```bash
./mvnw spring-boot:run
```

Or if using Windows:
```cmd
mvnw.cmd spring-boot:run
```

### 5. Access the Chatbot

1. Open your browser and navigate to: `http://localhost:8080`
2. You'll see a purple chat icon in the bottom-right corner
3. Click the icon to open the chatbot
4. Start chatting with your AI assistant!

## How It Works

### Backend Components

1. **ChatBotController** (`controller/ChatBotController.java`)
   - REST API endpoint: `POST /api/chatbot/chat`
   - Handles incoming chat messages
   - Returns AI-generated responses

2. **ChatBotService** (`service/ChatBotService.java`)
   - Interface defining chatbot operations

3. **ChatBotServiceImpl** (`serviceimpl/ChatBotServiceImpl.java`)
   - Implements Google Gemini API integration
   - Handles API calls and error management
   - Includes system instructions for context-aware responses

4. **DTOs** (`dto/`)
   - `ChatRequestDto`: Incoming message structure
   - `ChatResponseDto`: Outgoing response structure
   - `ChatMessageDto`: Message data model

### Frontend Components

1. **chatbot.css** (`static/css/chatbot.css`)
   - Modern, gradient-based design
   - Smooth animations and transitions
   - Responsive layout for all screen sizes

2. **chatbot.js** (`static/js/chatbot.js`)
   - Chatbot class with full functionality
   - Handles user input and API communication
   - Manages typing indicators and message display
   - XSS protection with HTML escaping

3. **Template Integration** (`templates/commons/`)
   - Added CSS link in `header.html`
   - Added JavaScript in `footer.html`

## Usage Examples

The chatbot can help with various queries about the Student Management System:

- "How do I add a new student?"
- "What features does this system have?"
- "How can I edit student information?"
- "What is this system used for?"
- "Tell me about student management"

## API Endpoints

### Send Chat Message
```http
POST /api/chatbot/chat
Content-Type: application/json

{
  "message": "Your question here"
}
```

**Response:**
```json
{
  "response": "AI generated response",
  "success": true,
  "error": null
}
```

### Health Check
```http
GET /api/chatbot/health
```

**Response:**
```
Chatbot service is running
```

## Customization

### Change Chatbot Personality

Edit the system instruction in `ChatBotServiceImpl.java`:
```java
String systemInstruction = "You are a helpful AI assistant for a Student Management System...";
```

### Change Model

Update `application.properties` to use a different Gemini model:
```properties
gemini.model=gemini-1.5-pro  # For more advanced responses
```

Available models:
- `gemini-1.5-flash` (recommended for free tier - faster, cost-effective)
- `gemini-1.5-pro` (more capable but uses more quota)

### Customize UI Colors

Edit `chatbot.css` and change the gradient colors:
```css
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
```

## Rate Limits (Free Tier)

Google Gemini free tier includes:
- 15 requests per minute (RPM)
- 1 million tokens per minute (TPM)
- 1,500 requests per day (RPD)

These limits are sufficient for development and small-scale deployments.

## Troubleshooting

### Chatbot not responding
1. Check if the API key is correctly configured
2. Verify internet connection
3. Check browser console for errors
4. Ensure the Spring Boot application is running

### "Unknown property" warnings
These warnings in `application.properties` are safe to ignore - they're custom properties for the Gemini configuration.

### Build errors after adding dependencies
Run:
```bash
./mvnw clean install -U
```

### CORS issues
The chatbot uses the same domain, so CORS shouldn't be an issue. If you deploy frontend and backend separately, add CORS configuration in `config.java`.

## Security Considerations

1. **API Key Protection**: Never expose your API key in client-side code
2. **Input Validation**: The backend validates all incoming messages
3. **XSS Prevention**: Frontend escapes all HTML in messages
4. **Rate Limiting**: Consider implementing rate limiting for production use

## Dependencies Added

```xml
<!-- Google Generative AI client -->
<dependency>
    <groupId>com.google.ai.client.generativeai</groupId>
    <artifactId>google-ai-generativeai</artifactId>
    <version>0.2.2</version>
</dependency>
```

## Future Enhancements

Consider these improvements:
- [ ] Add conversation history/memory
- [ ] Implement user authentication for personalized responses
- [ ] Add voice input/output support
- [ ] Store chat history in database
- [ ] Add file upload capabilities
- [ ] Implement multi-language support
- [ ] Add suggested questions/quick replies

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Review [Google Gemini API Documentation](https://ai.google.dev/docs)
3. Check Spring Boot logs for detailed error messages

## License

This chatbot integration follows the same license as the Student Management System project.
