# WirelessControl App 🔌  

Mouse Keyboard Shortcut Icon  

The **WirelessControl App** is an Android application designed for remote control of devices over a network connection. It includes features like a touchpad, keyboard input, and buttons for sending predefined commands such as "Enter" and "Backspace." By connecting to a server via IP address and port, it serves as a versatile tool for wireless control of compatible devices.  

## Features 🌟  

- **Touchpad Functionality**: Enables sending mouse movement data to the server using touch gestures.  
- **Keyboard Input**: Allows users to type text on the device and send it to the server.  
- **Predefined Commands**:  
  - "Enter"  
  - "Backspace"  
- **Server Connection**: Facilitates connection to a server using an IP address and port.  
- **Real-time Communication**: Ensures efficient communication between client and server.  

## Screenshots 📸  

Wireless Control Screenshot  

## How It Works 🔧  

1. **Connect to the Server**:  
   - Enter the server's IP address and port.  
   - Tap "Connect" to establish the connection.  

2. **Use the Touchpad**:  
   - Move your finger on the touchpad to send mouse movement data to the server.  

3. **Send Keyboard Input**:  
   - Type in the text field and tap "Send" to transmit text to the server.  
   - The text field clears automatically after sending.  

4. **Send Predefined Commands**:  
   - Tap the "Enter" or "Backspace" button to send these commands to the server.  

## Python Server 🐍  

The app communicates with a Python server, **`wirelesscontrol.py`**, located in the `server` folder of this repository.  

### Python Server Features  
- Processes touchpad input for mouse pointer control.  
- Simulates keyboard inputs, including typing text or pressing "Enter" and "Backspace."  

### Setting Up the Python Server  
1. Navigate to the `server` folder:  
   ```bash
   cd server
   ```
2. Install required Python libraries:  
   ```bash
   pip install pyautogui
   ```
3. Run the server script:  
   ```bash
   python wirelesscontrol.py
   ```
4. Input the server's IP and port in the app to establish the connection.

## Technology Stack 🛠

- **Programming Language**: Kotlin
- **Platform**: Android
- **Network Communication**: Sockets
- **Server-Side**: Python, pyautogui

## Setup and Installation 🛏

1. Clone this repository:  
   ```bash
   git clone https://github.com/AyushKumar-Codes/RemoteController.git
   ```
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.

## Usage Instructions 🔌

1. Ensure that the server is running and listening on the specified IP and port.
2. Launch the app and input the server's IP and port.
3. Use the touchpad, keyboard input, or predefined buttons to send commands.

## Contributing 📚

Contributions are welcome! If you find any issues or have suggestions for improvement:

1. Fork the repository.
2. Create a new branch:  
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:  
   ```bash
   git commit -m "Add feature description"
   ```
4. Push the branch:  
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

## License

This project is licensed under the MIT License.

## Acknowledgments 🙏

- **Kotlin & Android Development**: For providing tools to build this application.
- **Python & pyautogui**: For enabling server-side functionality.
- **Open Source Libraries**: Used to enhance app functionality.

## Contact 📢

If you have any questions or suggestions, feel free to reach out:

- **GitHub**: AyushKumar-Codes
- **Email**: kumarayush2470@outlook.com

Developed with ❤️ by Ayush Kumar

Citations:
[1] https://github.com/user-attachments/assets/ecf518cb-7ec4-4472-8a4f-e54cea3cf72f
