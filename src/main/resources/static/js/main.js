$(document).ready(init);

let score = 0;
var isPhoto = true;
var game = false;

function init(){

    $('#art-btn').hide();
    $('#photo-btn').hide();
    $('#photo').html('')
    $('#border-image').hide();


    $('#art-btn').click(artClick);
    $('#photo-btn').click(photoClick);
    $('#start-btn').click(startClick);
}

function showScore(){
    $('#score').html('Score: ' + score);
}

function getPicture(){
    $.ajax({
        method: 'POST',
        url: '/random-picture'
    }).done(function(imageData){
        let path = imageData.path;
        isPhoto = imageData.photo;
        showPicture(path);
    })
}

function showPicture(path){
    let photoContainer = $('#photo')
    photoContainer.html('<img src=/image/' + path + ' class="show-image"/>');
}

function startClick(){
    score = 0;
    $('#border-image').show();
    $('#art-btn').show();
    $('#photo-btn').show();
    $('#start-btn').hide();
    showScore();
    getPicture();
    game = true;
    setTimeout(function(){
        game = false;
        $('#border-image').hide();
        $('#art-btn').hide();
        $('#photo-btn').hide();
        $('#photo').html('');
        if(Cookies.get('highscore') < score || Cookies.get('highscore') === undefined){
            Cookies.set('highscore', score, { expires: 365, path: '' })
        }else{
            Cookies.set('highscore', Cookies.get('highscore'), { expires: 365, path: '' })
        }
    }, 5000)
    changeTime(5)
        setTimeout(function(){
            $('#start-btn').show();
        }, 1000)
}

function changeTime(timeLeft){
    $('#time').html('Time: ' + timeLeft);
    setTimeout(function(){
        if(timeLeft > 0){
           changeTime(timeLeft - 1) 
        }
    }, 1000)
}

function artClick(){
    if (isPhoto) {
        score -= 5;
    }else{
        score++;
    }
    showScore();
    hideBorder();
}

function photoClick(){
    if (isPhoto) {
        score++;
    }else{
        score -= 5;
    }
    showScore();
    hideBorder();
}

function hideBorder(){
    $('#border-image').hide();
    $('#art-btn').hide();
    $('#photo-btn').hide();
    setTimeout(function(){
        if(game){
            getPicture();
            $('#border-image').show();
            $('#art-btn').show();
            $('#photo-btn').show();
        }
    }, 1000);
}
