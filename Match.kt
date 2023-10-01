// data class to store a match and its data
// can I limit teams to two? not sure
data class Match(
    val teams: MutableList<Team> = mutableListOf(),
    val rallies: MutableList<Rally> = mutableListOf(),
    var pointsToWin: Int = 25
)

//store a player as a name
data class Player(
    val name: String,
    var ralliesErrored: Int = 0,
    var ralliesWon: Int = 0
)

//store a team as a list of players
data class Team(
    val players: MutableList<Player> = mutableListOf(),
    var name: String,
    var pointsScored: Int = 0
)

// store a rally's statistics (not really sure what I want here yet
data class Rally(
    val winner: Team,
    val loser: Team,
    val endedInWinner: Boolean,
    val endingPlayer: Player
)
