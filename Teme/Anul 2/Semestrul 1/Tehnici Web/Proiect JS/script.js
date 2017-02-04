window.onload = main;

var canvas, context, width, height, frames = 0, score = 0, currentState, okbtn, foregroundPosition = 0, timerId;
var best = localStorage.getItem("best") || 0;
var music;
var states = {
	Splash: 0, Game: 1, Score: 2
};

var bird = {
	x : 60,
	y: 0,
	frame: 0,
	velocity: 0,
	animation: [0, 1, 2, 1],
	rotation: 0, 
	radius: 12,
	gravity: 0.25,
	jumpValue: 4.6,
	
	Jump: function() {
		this.velocity = -this.jumpValue;
	},
	
	Update: function() {
		var n = currentState === states.Splash ? 10 : 5;
		this.frame += frames % n === 0 ? 1 : 0;
		this.frame %= this.animation.length;
		
		if (currentState === states.Splash) {
			this.y = height - 280 + 5 * Math.cos(frames / 10);
			this.rotation = 0;
		}
		else {
			this.velocity += this.gravity;
			this.y += this.velocity;
			this.HitsBottom();
			if (this.velocity >= this.jumpValue) {
				this.frame = 1;
				this.rotation = Math.min(Math.PI / 2, this.rotation + 0.3);
			}
			else {
				this.rotation = -0.3;
			}
		}
	},
	
	Draw: function(ctx) {
		ctx.save();
		ctx.translate(this.x, this.y);
		ctx.rotate(this.rotation);
		
		var n = this.animation[this.frame];
		s_bird[n].Draw(ctx, -s_bird[n].width / 2, -s_bird[n].height / 2);
		ctx.restore();
	},
	
	HitsBottom: function() {
		var rockBottom = height - 10;
		if (this.y >= rockBottom) {
			this.y = rockBottom;
			if (currentState === states.Game) {
				currentState = states.Score;
				alert("Your score is: " + score + "/ Best is: " + best);
			}
			this.velocity = this.jumpValue;
		}
	}
}

var pipes = {
	_pipes : [],
	
	Reset: function () {
		this._pipes = [];
	},
	
	Update: function() {
		if (frames % 100 === 0) {
			var _y = height - (s_upPipe.height + 120 + 200 * Math.random());
			this._pipes.push({
				x: 500,
				y: _y,
				width: s_upPipe.width,
				height: s_upPipe.height
			});
		}
		
		for (var i = 0, len = this._pipes.length; i < len; i++) {
			var pipe = this._pipes[i];
			
			if (i === 0) {
				score += pipe.x === bird.x ? 1 : 0;
				
				var cx  = Math.min(Math.max(bird.x, pipe.x), pipe.x + pipe.width);
				var cy1 = Math.min(Math.max(bird.y, pipe.y), pipe.y + pipe.height);
				var cy2 = Math.min(Math.max(bird.y, pipe.y + pipe.height + 80), pipe.y + 2 * pipe.height + 80);
				var dx  = bird.x - cx;
				var dy1 = bird.y - cy1;
				var dy2 = bird.y - cy2;
				var d1 = dx * dx + dy1 * dy1;
				var d2 = dx * dx + dy2 * dy2;
				var r = bird.radius * bird.radius;
				if (r > d1 || r > d2) {
					alert("Your score is: " + score + "/ Best is: " + best);
					currentState = states.Score;
				}
			}
			
			pipe.x -= 2;
			if (pipe.x < -pipe.width) {
				this._pipes.splice(i, 1);
				i--;
				len--;
			}
		}
	},
	
	Draw: function(ctx) {
		for (var i = 0, len = this._pipes.length; i < len; i++) {
			var pipe = this._pipes[i];
			s_upPipe.Draw(ctx, pipe.x, pipe.y);
			s_downPipe.Draw(ctx, pipe.x, pipe.y + 80 + pipe.height);
		}
	}
}

function OnPress(evt) {
	switch (currentState) {
		
		case states.Splash:
			currentState = states.Game;
			bird.Jump();
			break;
			
		case states.Game:
			bird.Jump();
			break;
			
		case states.Score:
			var mx = evt.offsetX, my = evt.offsetY;
			
			if (mx == null || my == null) {
				mx = evt.touches[0].clientX;
				my = evt.touches[0].clientY;
			}
			
			if (okbtn.x < mx && mx < okbtn.x + okbtn.width && 
				okbtn.y < my && my < okbtn.y + okbtn.height) {
				pipes.Reset();
				currentState = states.Splash;
				score = 0;
			}
			break;
	}
}

function Run() {
	var loop = function () {
		Update();
		Render();
		window.requestAnimationFrame(loop, canvas);
	}
	window.requestAnimationFrame(loop, canvas);
}

function Update() {
	frames++;
	
	best = Math.max(best, score);
	localStorage.setItem("best", best);
	
	if (currentState === states.Game) {
		pipes.Update();
	}
	
	bird.Update();
}

function Render () {
	context.fillRect(0, 0, width, height);
	
	pipes.Draw(context);
	bird.Draw(context);
	
	var centerWidth = canvas.width / 2;
	
	if (currentState === states.Splash) {
		s_splash.Draw(context, centerWidth - s_splash.width / 2, height - 300);
	}
	
	if (currentState === states.Score) {
		s_text.GameOver.Draw(context, centerWidth - s_text.GameOver.width / 2, height - 400);
		s_buttons.Ok.Draw(context, okbtn.x, okbtn.y);

	} else {
		s_numberB.Draw(context, null, 20, score, centerWidth);

	}
}

function UpdateTime() {
	var date = new Date();
	var hours = date.getHours()
	
	if (hours < 10) hours = '0' + hours;
	document.getElementById('hour').innerHTML = hours;
 
	var minutes = date.getMinutes();
	if (minutes < 10) minutes = '0' + minutes;
	document.getElementById('min').innerHTML = minutes;
 
	var seconds = date.getSeconds();
	if (seconds < 10) seconds = '0' + seconds;
	document.getElementById('sec').innerHTML = seconds;
}

function ClockStart() {  
	if (timerId) return;
	timerId = setInterval(UpdateTime, 1000);
	UpdateTime();
}

function ClockStop() {
	clearInterval(timerId);
	timerId = null;
}

function main() {
	
	music = new sound("Resources/music.mp3");
    setTimeout(function() {
			music.play()
	}, 10000);
	
	canvas = document.createElement("canvas");
	
	width = window.innerWidth;
	height = window.innerHeight;
	
	var evt = "touchstart";
	
	if (width >= 500) {
		width  = 320;
		height = 480;
		canvas.style.border = "1px solid #000";
		evt = "mousedown";
	}
	
	canvas.width = width;
	canvas.height = height;
	canvas.id ="cvs2"
	
	context = canvas.getContext("2d");
	currentState = states.Splash;
	var divClock = document.getElementById("clock");
	document.body.insertBefore(canvas, divClock);
	
	var cvs = document.getElementById("cvs2");
	cvs.addEventListener(evt, OnPress);
	
	ClockStart();
	
	var img = new Image();
	img.onload = go(img);	
}

function go(img) {
	InitSprites(img);
	context.fillStyle = "#213b3e";
		
	okbtn = {
		x: (width - s_buttons.Ok.width) / 2,
		y: height - 200,
		width: s_buttons.Ok.width,
		height: s_buttons.Ok.height
	}
		
	Run();	
		
	img.src = "Resources/sheet.png";
}

function sound(src) {
    this.sound = document.createElement("audio");
    this.sound.src = src;
    this.sound.setAttribute("preload", "auto");
    this.sound.setAttribute("controls", "none");
    this.sound.style.display = "none";
    document.body.appendChild(this.sound);
    this.play = function(){
        this.sound.play();
    }
    this.stop = function(){
        this.sound.pause();
    }    
}

var s_bird, s_upPipe, s_downPipe, s_text, s_score, s_buttons, s_numberS, s_numberB;

function Sprite(img, x, y, width, height) {
	this.img = img;
	this.x = x * 2;
	this.y = y * 2;
	this.width = width * 2;
	this.height = height * 2;
};

Sprite.prototype.Draw = function(ctx, x, y) {
	ctx.drawImage(this.img, this.x, this.y, this.width, this.height,
		x, y, this.width, this.height);
};

function InitSprites(img) {

	s_bird = [
		new Sprite(img, 156, 115, 17, 12),
		new Sprite(img, 156, 128, 17, 12),
		new Sprite(img, 156, 141, 17, 12)
	];
	
	s_upPipe = new Sprite(img, 251, 0, 26, 200);
	s_downPipe = new Sprite(img, 277, 0, 26, 200);
	
	s_text = {
		FlappyBird: new Sprite(img, 59, 114, 96, 22),
		GameOver:   new Sprite(img, 59, 136, 94, 19),
		GetReady:   new Sprite(img, 59, 155, 87, 22)
	}
	s_buttons = {
		Rate:  new Sprite(img,  79, 177, 40, 14),
		Menu:  new Sprite(img, 119, 177, 40, 14),
		Share: new Sprite(img, 159, 177, 40, 14),
		Score: new Sprite(img,  79, 191, 40, 14),
		Ok:    new Sprite(img, 119, 191, 40, 14),
		Start: new Sprite(img, 159, 191, 40, 14)
	}

	s_score  = new Sprite(img, 138,  56, 113, 58);
	s_splash = new Sprite(img,   0, 114,  59, 49);

	s_numberS = new Sprite(img, 0, 177, 6,  7);
	s_numberB = new Sprite(img, 0, 188, 7, 10);

	s_numberS.Draw = s_numberB.Draw = function(context, x, y, num, center, offset) {
		num = num.toString();

		var step = this.width + 2;
		
		if (center) {
			x = center - (num.length * step - 2) / 2;
		}
		if (offset) {
			x += step * (offset - num.length);
		}

		for (var i = 0, len = num.length; i < len; i++) {
			var n = parseInt(num[i]);
			context.drawImage(img, step * n, this.y, this.width, this.height,
				x, y, this.width, this.height)
			x += step;
		}
	}
}

$(document).ready(function() {
	$("div").click(function() {
		$(this).hide();
	});
});

