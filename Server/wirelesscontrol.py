import socket
import pyautogui

# Server configuration
HOST = '0.0.0.0'  # Listen on all available network interfaces
PORT = 12345      # Port to listen on

def handle_client(client_socket):
    """
    Handles communication with the connected client.
    """
    try:
        with client_socket:
            buffer = ""
            while True:
                # Receive data in chunks
                data = client_socket.recv(1024)
                if not data:
                    break  # Connection closed by client

                # Append received data to the buffer
                buffer += data.decode('utf-8')

                # Process complete commands separated by newlines
                while '\n' in buffer:
                    command, buffer = buffer.split('\n', 1)  # Split at the first newline
                    process_command(command.strip())
    except Exception as e:
        print(f"Connection lost: {e}")

def process_command(command):
    """
    Processes an individual command received from the client.
    """
    try:
        pyautogui.FAILSAFE = False
        cmd_type, *args = command.split('|')
        if cmd_type == "MOVE":
            if len(args) == 2:  # Ensure proper arguments are provided
                dx, dy = map(float, args)
                pyautogui.moveRel(dx, dy, duration=0.1)
        elif cmd_type == "TEXT":
            if args:
                text = args[0]
                pyautogui.typewrite(text)
        elif cmd_type == "ENTER":
            pyautogui.press('enter')
        elif cmd_type == "BKSP":
            pyautogui.press('backspace')
        else:
            print(f"Unknown command type: {cmd_type}")
    except Exception as e:
        print(f"Error processing command '{command}': {e}")


def start_server():
    """
    Starts the server and listens for incoming client connections.
    """
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((HOST, PORT))
        server_socket.listen()
        print(f"Server listening on {HOST}:{PORT}")
        while True:
            client_socket, client_address = server_socket.accept()
            print(f"Connection from {client_address}")
            handle_client(client_socket)

if __name__ == "__main__":
    start_server()


