<div class="row">
	<div class="col-md-4 offset-md-4">
		<h1 class="text-center login-title">Login Here</h1>
		<div class="account-wall">
			<form class="form-signin" action="/login" method="POST">
				<input class="form-control" type="text" name="username"
					placeholder="Username" required="required" autofocus="autofocus" />
				<input class="form-control" type="password" name="password"
					placeholder="Password" required="required" />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
				<span class="clearfix"></span>
			</form>
		</div>
		<a class="text-center" href="/forget_pw">Forgot your password?</a>
		<div id="message"></div>
		</h1>
	</div>
</div>