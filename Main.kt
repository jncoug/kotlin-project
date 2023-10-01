fun main() {
    //match is a data class to store the pickleball stats
    val match = Match()
    //give match to controller to manipulate the match stats
    val matchController = MatchController(match)

    println("Welcome to the Pickleball Match Recorder!")

    // go until "return" is called
    while (true) {
        println("Options:")
        println("1. Start a new match")
        println("2. Display Match Stats")
        println("3. Quit")
        print("Enter your choice: ")

        // "when" is a way to have a conditional statement, it checks the value in the parentheses to see if it matches
        // one of the conditions then executes if a match is found

        // this gets an input that may or may not be null, and converts it to an integer safely
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> matchController.startNewMatch()
            2 -> {
                // display match stats or something
                matchController.displayStats()
            }
            3 -> {
                println("Thank you for using the Pickleball Match Recorder!")
                return
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}