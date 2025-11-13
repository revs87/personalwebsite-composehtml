#!/bin/bash


# Capture high-resolution start time (seconds.nanoseconds) using a format compatible with bc.
START_TIME=$(date +%s.%N)

# Function to calculate and display elapsed time with milliseconds
function display_elapsed_time {
    local END_TIME=$(date +%s.%N)

    # Calculate duration in seconds (float) using 'bc' for precision
    local DURATION=$(echo "$END_TIME - $START_TIME" | bc)

    # Awk is used for reliable floating-point math and precise formatting.
    echo "$DURATION" | awk '
        {
            # Convert the duration (e.g., 10.512) to total milliseconds (e.g., 10512)
            MS = int($1 * 1000);

            TOTAL_SECONDS = int(MS / 1000);

            hours = int(TOTAL_SECONDS / 3600);
            minutes = int((TOTAL_SECONDS % 3600) / 60);
            seconds = int(TOTAL_SECONDS % 60);
            milliseconds = MS % 1000;

            # Output format: HHh MMm SSs MSms
            printf "\n\n✅ Total Build Time: %02dh %02dm %02ds %03dms\n", hours, minutes, seconds, milliseconds;
        }
    '
}


# Define the Gradle wrapper command based on the environment (Linux/macOS)
GRADLEW="/opt/personalwebsite/personalwebsite-composehtml/gradlew"
if [ ! -f "$GRADLEW" ] || [ ! -x "$GRADLEW" ]; then
    echo "Error: Gradle wrapper not found or is not executable."
    echo "Ensure you are running this script from the project root directory."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi
KOBWEB_CLI="/home/admin/.sdkman/candidates/kobweb/current/bin/kobweb"
if [ ! -f "$KOBWEB_CLI" ] || [ ! -x "$KOBWEB_CLI" ]; then
    echo "Error: Kobweb CLI not found or is not executable."
    echo "Ensure you are running this script from the project root directory."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi
$GRADLEW --version
$KOBWEB_CLI --version
cd /opt/personalwebsite/personalwebsite-composehtml


echo -e "\n\n----- rerun 1/3 ----- Stopping Kotlin Daemon (Fixes cache lock issues) ---------"
# This stops all Kotlin compiler daemon processes that might be holding onto file handles.
$GRADLEW --stop


echo -e "\n\n----- rerun 2/3 ----- Stop running server if present ---------"
$GRADLEW kobwebStop


echo -e "\n\n----- rerun 3/3 ----- Running (Non-Interactive) start.sh in $(pwd) ---------"
# Prod environment still has issues
#$KOBWEB_CLI run -p site --layout fullstack --env dev
sh site/.kobweb/server/start.sh


# Note: The server will run until you stop it (e.g., Ctrl+C).