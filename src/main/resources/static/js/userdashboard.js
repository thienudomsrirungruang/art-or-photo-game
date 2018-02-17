$(document).ready(init);


let username;

function init(){
    username = window.location.pathname.split('/')[2];
    
    displayPlayCount();
    displayUsernameStats();

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