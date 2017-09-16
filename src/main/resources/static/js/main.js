$(document).ready(init);

let score = 0;

function init(){
    showScore();
    $('#art-btn').click(artClick);
    $('#photo-btn').click(photoClick);
}

function showScore(){
    $('#score').html('Score: ' + score);
}

function artClick(){
    score++;
    showScore();
}

function photoClick(){
    score--;
    showScore();
}