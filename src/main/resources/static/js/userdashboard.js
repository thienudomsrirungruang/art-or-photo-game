$(document).ready(init);

let username;

function processUserChange(){
    var user = $('#player_name').val();
    window.location.href = '/dashboard/' + user;
}

function init(){
    username = window.location.pathname.split('/')[2];
    checkUserExists();

    $('#player_name').keypress(function(e){
        if (e.which == 13) {
            processUserChange();
        }
    })

}

function displayDashboard(){
    displayPlayCount();
    displayUsernameStats();

    displayArtOrPhotoGameCounts();
}

function checkUserExists(){
    $.ajax({
        method: 'POST',
        url: '/user/' + username,
    }).done(function(exists){
        if(exists){
            displayDashboard();
        }else{
            $('#dashboard').html('This user doesn\'t exist.')
        }
    })
}

function displayPlayCount(){
    $.ajax({
        method: 'POST',
        url: '/user/' + username + '/playcount',
        contentType:'application/json'
    }).done(function(count){
        $('#playcount').html('Total games played: ' + count);
    })
}

function displayUsernameStats(){
    $('#username').html('Username: ' + username);
}

function displayArtOrPhotoGameCounts(){
    $.ajax({
        method: 'POST',
        url: '/user/' + username + '/playcount/1',
        contentType:'application/json'
    }).done(function(count){
        $('#art-or-photo-playcount').html('Games played: ' + count);
    })
}