$('document').ready(redirect);

function redirect(){
    $.ajax({
        method: 'POST',
        url: '/user/getusername'
    }).done(function(username){
        window.location.href = '/dashboard/' + username;
    })
}