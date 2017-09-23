$(document).ready(init);

let score = 0;
var isPhoto = true;

function init(){
    getPicture();
    $('#art-btn').click(artClick);
    $('#photo-btn').click(photoClick);
}

function showScore(){
    $('#score').html('Score: ' + score);
}

function getPicture(){
    showScore();
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
    photoContainer.html('<img src=' + path + ' class="show-image"/>');
}

function artClick(){
    if (isPhoto) {
        score--;
        getPicture();
    }else{
        score++;
        getPicture();
    }
    
}

function photoClick(){
    if (isPhoto) {
        score++;
        getPicture();
    }else{
        score--;
        getPicture();
    }
}
