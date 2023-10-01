// MatchController is a class that is designed to manipulate the match data class
class MatchController(private val match: Match) {

    private var isTeam1Serving = true // Initialize with team 1 serving
    private var isTeam1Winning = true // Initialize with team 1 winning

    private lateinit var winningTeam: Team
    private var matchOver = false;

    // function to initialize the match data, with teams and players, etc
    fun startNewMatch() {

        // add team names
        println("Enter team name for team 1 (or hit enter to skip):")
        val team1Name = readlnOrNull()
        if (team1Name != null) {
            match.teams.add(Team(name = team1Name))
        }
        println("Enter team name for team 2 (or hit enter to skip):")
        val team2Name = readlnOrNull()
        if (team2Name != null) {
            match.teams.add(Team(name = team2Name))
        }

        //go through teams and add players to the team
        for (i in 0..1) {
            print("Enter team $i player names (or leave empty to finish): ")
            for (j in 0..1) {
                val playerName = readlnOrNull()
                if (playerName.isNullOrBlank()) break
                match.teams[i].players.add(Player(playerName))
            }


        }

        // get number of games
        print("Enter the number of points needed to win: ")
        match.pointsToWin = readlnOrNull()?.toIntOrNull() ?: 25



    }

    fun displayStats() {
        println("Winning team: ${winningTeam.name}")

        println("Teams:")
        for (team in match.teams) {
            println("${team.name}'s players:")
            for (player in team.players) {
                println(player.name)
                println("Rallies won: ${player.ralliesWon}")
                println("Rallies won: ${player.ralliesWon}")
            }
        }
    }



    // ... other functions ...

    fun recordRally() {
        if (match.pointsToWin == 0) {
            println("Please start a new match first.")
            return
        }
        println("Which team is serving first? ${match.teams[0].name}: (1) or ${match.teams[1].name}: (2)?")
        val serveNum = readln().toIntOrNull()
        val servingTeam = if (serveNum == 1) match.teams[0] else match.teams[1]
        val receivingTeam = if (serveNum == 2) match.teams[1] else match.teams[0]


        while (!matchOver) {
            println("Serving Team: ${servingTeam.name}")
            println("Receiving Team: ${receivingTeam.name}")

            println("Enter the winning team (1 or 2): ")
            val winningTeam = readLine()?.toIntOrNull()

            if (winningTeam != null && (winningTeam == 1 || winningTeam == 2)) {
                // Update the serving team if the returning team won the previous rally
                if (winningTeam != if (isTeam1Winning) 1 else 2) {
                    isTeam1Serving = !isTeam1Serving
                }

                // ... rest of the rally recording logic ...

                // Update the isTeam1Winning flag based on the current winning team
                isTeam1Winning = winningTeam == 1
            } else {
                println("Invalid team. Please enter 1 or 2.")
            }


            val endingPlayer = servingTeam.players[0]

            // input how the point ended
            val endedInWinner = true

            match.rallies.add(Rally(servingTeam, receivingTeam, endedInWinner, endingPlayer))
            if (winningTeam != null) {
                println("${match.teams[winningTeam-1]} won the rally. Rally recorded.")
                match.teams[winningTeam-1].pointsScored++
            }

            // Update player statistics
            if (endedInWinner) {
                endingPlayer.ralliesWon++
            } else {
                endingPlayer.ralliesErrored++
            }
        }
            for (team in match.teams) {
                if (team.pointsScored>=25) {
                    matchOver = true
                    winningTeam = team
                }
            }

        }

}