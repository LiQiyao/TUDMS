$(function(){

    // based on http://codepen.io/dleatherman/pen/kAzgw

    var header = $('.site-header'),
        canvas = $('<canvas></canvas>').appendTo(header)[0],
        ctx    = canvas.getContext('2d'),
        color  = 'rgb(103,216,241)',
        idle   = null,
        mousePosition;

    canvas.width         = window.innerWidth;
    canvas.height        = header.outerHeight();
    canvas.style.display = 'block';
    canvas.style.margin  = '0 auto';

    ctx.fillStyle = color;
    ctx.lineWidth = .1;
    ctx.strokeStyle = color;

    mousePosition = {
        // x: 50 * canvas.width / 100,
        // y: 50 * canvas.height / 100
        x:-1000,
        y:-1000
    };
    var dots = {
        nb: (canvas.width < 768 ? 50 : 150),
        distance: 80, d_radius: 150,
        array: []
    };

    function Dot(){
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;

        this.vx = -.5 + Math.random();
        this.vy = -.5 + Math.random();

        this.radius = Math.random();
    }

    Dot.prototype = {
        create: function(){
            ctx.beginPath();
            ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
            ctx.fill();
        },

        animate: function(){

            for(var i = 0; i < dots.nb; i++){

                var dot = dots.array[i];

                if(dot.y < 0 || dot.y > canvas.height){
                    dot.vx = dot.vx;
                    dot.vy = - dot.vy;
                }
                else if(dot.x < 0 || dot.x > canvas.width){
                    dot.vx = - dot.vx;
                    dot.vy = dot.vy;
                }
                dot.x += dot.vx;
                dot.y += dot.vy;
            }
        },

        line: function(){
            for(i = 0; i < dots.nb; i++){
                for(j = 0; j < dots.nb; j++){
                    i_dot = dots.array[i];
                    j_dot = dots.array[j];

                    if((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > - dots.distance && (i_dot.y - j_dot.y) > - dots.distance){
                        if((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > - dots.d_radius && (i_dot.y - mousePosition.y) > - dots.d_radius){
                            ctx.beginPath();
                            ctx.moveTo(i_dot.x, i_dot.y);
                            ctx.lineTo(j_dot.x, j_dot.y);
                            ctx.stroke();
                            ctx.closePath();
                        }
                    }
                }
            }
        }
    };

    function createDots(){
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        for(i = 0; i < dots.nb; i++){
            dots.array.push(new Dot());
            dot = dots.array[i];

            dot.create();
        }
        dot.line();
        dot.animate();

        var tmpWidth;
        if(window.innerWidth>1350) tmpWidth=canvas.width;
        else tmpWidth=window.innerWidth;
        ctx.textBaseline = 'middle';//设置文本的垂直对齐方式
        ctx.textAlign = 'center'; //设置文本的水平对对齐方式
        ctx.font="30px Arial";
        ctx.fillText("Connecting People", tmpWidth / 2,canvas.height/4);
        ctx.font="20px 微软雅黑";
        ctx.fillText("科技以人为本", tmpWidth / 2,canvas.height/2);

    }

    idle = setInterval(createDots, 1000/30);

    $(canvas).on('mousemove mouseleave', function(e){
        if(e.type == 'mousemove'){
            mousePosition.x = e.pageX;
            mousePosition.y = e.pageY;
        }
        if(e.type == 'mouseleave'){
            mousePosition.x = canvas.width / 2;
            mousePosition.y = canvas.height / 2;
        }
    });
});