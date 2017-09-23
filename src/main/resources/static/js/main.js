$(document).ready(init);

let score = 0;
var isPhoto = true;

function init(){
    showScore();
    getPicture();
    $('#art-btn').click(artClick);
    $('#photo-btn').click(photoClick);
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
        console.log(isPhoto);
    })
}

function showPicture(path){
    let photoContainer = $('#photo')
    photoContainer.html('<img src=/image/' + path + ' class="show-image"/>');
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
        getPicture();
        $('#border-image').show();
        $('#art-btn').show();
        $('#photo-btn').show();
    }, 1000);
}
