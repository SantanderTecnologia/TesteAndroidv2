Fiz o Teste App Android V2 em java no padrão MVP (inicialmente ia usar MVP + DDD criando um domain, mas achei que não tinha necessidade do domain).
Passos que foram essenciais para o funcionamento ao app: permissão de internet no Manisfest; uso do Retrofit e Gson que coloquei nas dependências do gradle; uso do RecyclerView na segunda tela (mais performático que o ListView); uso do regex para validar usuário e senha; uso do SharedPreferences para salvar usuário e senha; uso do ProgressBar para melhor experiência do usuário que aguarda as informações do RecyclerView.
PARA USAR O APP: para logar digite um email ou CPF válidos no campo de usuário e uma senha no campo de senha. Assim que logar as informações do usuário seram salvas e ao fazer logout (botão no canto superior direito) essas infomações aparecerão na tela de login.  
