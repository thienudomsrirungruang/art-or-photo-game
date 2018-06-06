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
    var GAME_INFO = {
        id: 1,
        name: 'Art or Photo'
    };
    displayPlayCount();
    displayUsernameStats();

    displayGameCounts(GAME_INFO);
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

function displayGameCounts(game){
    var display = '';
    display += '<div class="col s12 m6"><div class="info-box medium">' + game.name;
    $.ajax({
        method: 'POST',
        url: '/user/' + username + '/playcount/' + game.id,
        contentType:'application/json'
    }).done(function(count){
        display += '<div class="game-playcount">Games played: ' + count + "</div>";
        if(count > 0){
            $.ajax({
                method: 'POST',
                url: '/user/' + username + '/highscore/' + game.id,
                contentType:'application/json'
            }).done(function(score){
                display += '<div class="game-highscore">High score: ' + score + "</div>";
                $.ajax({
                    method: 'POST',
                    url: '/user/' + username + '/recentscores/' + game.id,
                    contentType: 'application/json'
                }).done(function(recentscores){
                    if(count > 1){
                        display += '<div class="chart"><canvas class="chart" id="chart-' + game.id + '"></canvas></div>';
                    }
                    display += '</div></div>';
                    $('#game-counts').html(display);
                    if(count > 1){
                        initChart('chart-' + game.id, recentscores);
                    }
                })
            })
        }else{
            display += '</div></div>'
            $('#game-counts').html(display);
        }
    })
}

function initChart(divID, recentscores){
    console.log(recentscores);
    var labels = [];
    for (var i = 0; i < recentscores.length; i++) {
        labels.push('');
    }
    var ctx = $('#' + divID);
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: "Score",
                data: recentscores,
                // backgroundColor: [
                // 'rgba(255, 99, 132, 0.2)',
                // 'rgba(54, 162, 235, 0.2)',
                // 'rgba(255, 206, 86, 0.2)',
                // 'rgba(75, 192, 192, 0.2)',
                // 'rgba(153, 102, 255, 0.2)',
                // 'rgba(255, 159, 64, 0.2)'
                // ],
                // borderColor: [
                //     'rgba(255,99,132,1)',
                //     'rgba(54, 162, 235, 1)',
                //     'rgba(255, 206, 86, 1)',
                //     'rgba(75, 192, 192, 1)',
                //     'rgba(153, 102, 255, 1)',
                //     'rgba(255, 159, 64, 1)'
                // ],
                borderWidth: 1
            }]
        },
        options: {
            legend: {
                display: false
            }
        }
    });
}

//                <div class="col s12 m6">
//                    <div class="info-box medium">
//                        Art Or Photo
//                        <div id="art-or-photo-playcount">
//                        </div>
//                        <div id="art-or-photo-highscore">
//                        </div>
//                    </div>
//                </div>