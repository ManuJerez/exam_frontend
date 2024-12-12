import { auth, GoogleProvider } from '../firebase/firebase'
import { signInWithPopup } from 'firebase/auth'
import { usuarioService } from '../services/usuario'
import { useNavigate } from 'react-router-dom'

const Login = () => {
	const navigate = useNavigate();

	const handleLogIn = async () => {
		try {
			const res = await signInWithPopup(auth, GoogleProvider)
			const user = res.user;
			console.log(user);
			usuarioService.loginUsuario(user);
			navigate('/');
		} catch (error) {
			console.error("Error al iniciar sesion: ", error);
		}
	};

	return (
		<>
			<div className='login-container'>
				<h2>Login</h2>
				<button className='login-button' onClick={handleLogIn}>Login with Google</button>
			</div>
		</>
	)
}

export default Login