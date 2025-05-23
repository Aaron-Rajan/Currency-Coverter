# Currency Converter 💸

A desktop application that allows users to convert currencies using a graphical user interface (GUI) built with Java Swing. The application integrates with an external API to fetch real-time exchange rates and uses a local JSON file for currency data.

## Features

- **User-Friendly Interface**: Intuitive GUI developed with Java Swing.
- **Real-Time Exchange Rates**: Retrieves live conversion rates using an external API.
- **Offline Support**: Uses a local `currencies.json` file for currency metadata.
- **Wide Currency Support**: Enables conversions across numerous global currencies.

## Technologies Used

- **Java** – Core language for the app's functionality.
- **Java Swing** – For building the GUI.
- **JSON** – Used to store and read local currency data.
- **HTTP Requests** – Used to fetch data from a public currency conversion API.

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Aaron-Rajan/Currency-Coverter.git
   ```

2. **Navigate to the Project Directory**
   ```bash
   cd Currency-Coverter
   ```

3. **Compile the Java Program**
   ```bash
   javac GUI.java
   ```

4. **Run the Application**
   ```bash
   java GUI
   ```

## Usage

1. Launch the app by running the `GUI` class.
2. Select your source and target currencies from the dropdown menus.
3. Enter the amount to convert.
4. Click the **Convert** button to view the result based on the latest exchange rate.

## Project Structure

- `GUI.java` – Contains the main Java Swing code and business logic.
- `currencies.json` – Stores metadata about supported currencies.
- `.vscode/` – Editor-specific configuration files (optional).
