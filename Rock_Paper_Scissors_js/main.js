function getComputerChoice () {
    const choice = Math.random()
    if(choice < (1/3)) {
        return "rock";
    }
    else if(choice < 2/3) {
        return "paper";
    }
    else {
        return "scissors";
    }
}
function playRound(player, comp) {
    if(player===comp) {
        return "It's a draw!";
    }
    else if(player==="rock") {
        if(comp==="paper") {
            return "You lost!";
        }
        else { //scissors
            return "You won!";
        }
    }
    else if (player==="paper") {
        if(comp==="rock") {
            return "You won!";
        }
        else { //scissors
            return "You lost!";
        }
    }
    else { //scissors
        if(comp==="paper") {
            return "You won!";
        }
        else { //rock
            return "You lost!";
        }
    }
}
function gameover(ps, cs) {
    if(ps == 5) {
        playerscores.textContent = "You won!";
        computerscores.textContent = "";

    }
    else if(cs == 5) {
        playerscores.textContent = "You lost!";
        computerscores.textContent = "";
    }   
}
function game() {
    let playerscore = 0;
    let compscore = 0;
    let temp = "";
    const rock = document.getElementById('rockbtn');
    rock.addEventListener('click', () => {
        temp = playRound("rock", getComputerChoice());
        if(temp === "You won!") {
            playerscore +=1;
        }
        else if(temp === "You lost!"){
            compscore +=1;
        }
        playerscores.textContent = "Player: " + playerscore.toString();
        computerscores.textContent = "Computer: " + compscore.toString();
        gameover(playerscore, compscore); 
    });
    const paper = document.getElementById('paperbtn');
    paper.addEventListener('click', () => {
        temp = playRound("paper", getComputerChoice());
        if(temp === "You won!") {
            playerscore +=1;
        }
        else if(temp === "You lost!"){
            compscore +=1;
        }
        playerscores.textContent = "Player: " + playerscore.toString();
        computerscores.textContent = "Computer: " + compscore.toString();
        gameover(playerscore, compscore);
    });
    const scissors = document.getElementById('scissorsbtn');
    scissors.addEventListener('click', () => {
        temp = playRound("scissors", getComputerChoice());
        if(temp === "You won!") {
            playerscore +=1;
        }
        else if(temp === "You lost!"){
            compscore +=1;
        }
        playerscores.textContent = "Player: " + playerscore.toString();
        computerscores.textContent = "Computer: " + compscore.toString();
        gameover(playerscore, compscore);
    });
}
window.onload = function() {
    game();
}

