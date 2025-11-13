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
# Check if the wrapper is executable
if [ ! -f "$GRADLEW" ] || [ ! -x "$GRADLEW" ]; then
    echo "Error: Gradle wrapper not found or is not executable."
    echo "Ensure you are running this script from the project root directory."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi
cd /opt/personalwebsite/personalwebsite-composehtml


echo -e "\n\n----- rebuild 1/7 ----- Checkout branch ---------"
# Pull the latest changes from the current branch
git checkout dev

# Check for checkout success
if [ $? -ne 0 ]; then
    echo "Git checkout failed. Please resolve conflicts or issues before proceeding."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi


echo -e "\n\n----- rebuild 2/7 ----- Pulling latest changes from Git ---------"
# Pull the latest changes from the current branch
git pull

# Check for pull success
if [ $? -ne 0 ]; then
    echo "Git pull failed. Please resolve conflicts or issues before proceeding."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi


echo -e "\n\n----- rebuild 3/7 ----- Cleaning all previous build artifacts ---------"
# Clean the entire project build directory
$GRADLEW clean

# Check for clean success
if [ $? -ne 0 ]; then
    echo "Gradle clean failed."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi


echo -e "\n\n----- rebuild 4/7 ----- Stopping Kotlin Daemon (Fixes cache lock issues) ---------"
# This stops all Kotlin compiler daemon processes that might be holding onto file handles.
$GRADLEW --stop


echo -e "\n\n----- rebuild 5/7 ----- Stop running server if present ---------"
$GRADLEW kobwebStop


echo -e "\n\n----- rebuild 6/7 ----- Building sources and resources ---------"
# Added --no-build-cache and --rerun-tasks to force a complete, non-incremental build
$GRADLEW build --no-build-cache --rerun-tasks

# Check for build success
if [ $? -ne 0 ]; then
    echo "Gradle build failed."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi


echo -e "\n\n----- rebuild 7/7 ----- Building kobweb export tasks ---------"
$GRADLEW :site:kobwebBuildOnly

# Check for build success
if [ $? -ne 0 ]; then
    echo "Gradle kobwebBuildOnly failed."
    # Display time before exiting on error
    display_elapsed_time
    exit 1
fi


# Display the total time taken for all preparation and build steps
display_elapsed_time


# Note: The server will run until you stop it (e.g., Ctrl+C).