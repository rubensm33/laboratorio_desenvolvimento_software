# laboratório_desenvolvimento_software

# Histórias de Usuário

## História 1: Login de Usuário
**Como** um usuário (aluno, professor, ou administrador),  
**eu quero** fazer login no sistema usando minha senha,  
**para** acessar minhas funcionalidades específicas.

### Critérios de Aceitação:
1. O sistema deve permitir o login apenas se o usuário fornecer um nome de usuário e uma senha válidos.
2. O sistema deve exibir uma mensagem de erro se o nome de usuário ou a senha estiver incorreto.
3. Após um login bem-sucedido, o sistema deve redirecionar o usuário para a página principal apropriada (aluno, professor ou administrador).
4. O sistema deve bloquear o acesso após 3 tentativas de login incorretas e enviar uma notificação de segurança ao administrador.

## História 2: Cadastro de Disciplinas pelo Administrador
**Como** um administrador do sistema,  
**eu quero** cadastrar disciplinas para o semestre,  
**para** que os alunos possam se matricular nessas disciplinas.

### Critérios de Aceitação:
1. O administrador deve ser capaz de inserir informações da disciplina, incluindo nome, número de créditos e semestre.
2. O sistema deve validar que o número de créditos e outras informações inseridas estão corretas e no formato adequado.
3. O administrador deve receber uma confirmação quando a disciplina for cadastrada com sucesso.
4. O sistema deve permitir a visualização e edição das disciplinas cadastradas.

## História 3: Matricular-se em Disciplinas Obrigatórias
**Como** um aluno,  
**eu quero** me matricular em até 4 disciplinas obrigatórias,  
**para** garantir minha presença nas disciplinas necessárias para o meu curso.

### Critérios de Aceitação:
1. O sistema deve permitir que o aluno se matricule em até 4 disciplinas obrigatórias por semestre.
2. O sistema deve verificar se a disciplina é obrigatória para o aluno antes de permitir a matrícula.
3. O sistema deve mostrar uma mensagem de erro se o aluno tentar se matricular em mais de 4 disciplinas obrigatórias.
4. O sistema deve mostrar uma confirmação de matrícula bem-sucedida.

## História 4: Matricular-se em Disciplinas Optativas
**Como** um aluno,  
**eu quero** me matricular em até 2 disciplinas optativas,  
**para** complementar meus estudos com matérias de minha escolha.

### Critérios de Aceitação:
1. O sistema deve permitir que o aluno se matricule em até 2 disciplinas optativas por semestre.
2. O sistema deve verificar se a disciplina é optativa para o aluno antes de permitir a matrícula.
3. O sistema deve mostrar uma mensagem de erro se o aluno tentar se matricular em mais de 2 disciplinas optativas.
4. O sistema deve mostrar uma confirmação de matrícula bem-sucedida.

## História 5: Visualizar Alunos Matriculados
**Como** um professor,  
**eu quero** acessar a lista de alunos matriculados em minhas disciplinas,  
**para** saber quem estará frequentando minhas aulas.

### Critérios de Aceitação:
1. O professor deve ser capaz de visualizar a lista completa de alunos matriculados em suas disciplinas.
2. O sistema deve garantir que apenas o professor da disciplina possa visualizar essa lista.
3. A lista deve incluir informações como nome e número de matrícula dos alunos.
4. O sistema deve permitir que o professor atualize a lista em tempo real.

## História 6: Cancelamento de Disciplinas
**Como** um aluno,  
**eu quero** cancelar minha matrícula em uma disciplina,  
**para** ajustar meu cronograma conforme minhas necessidades.

### Critérios de Aceitação:
1. O sistema deve permitir que o aluno cancele sua matrícula em qualquer disciplina, desde que não exceda o número de disciplinas matriculadas permitido.
2. O sistema deve atualizar imediatamente a lista de matrículas e as opções disponíveis para outros alunos.
3. O sistema deve confirmar o cancelamento da matrícula e mostrar uma mensagem de sucesso.
4. O cancelamento deve ser refletido na contagem de alunos inscritos na disciplina.

## História 7: Notificação ao Sistema de Cobranças
**Como** o sistema de matrículas,  
**eu quero** notificar o sistema de cobranças quando um aluno se matricular,  
**para** garantir que o aluno seja cobrado adequadamente pelas disciplinas matriculadas.

### Critérios de Aceitação:
1. O sistema de matrículas deve enviar uma notificação para o sistema de cobranças automaticamente após a matrícula ser confirmada.
2. A notificação deve incluir detalhes sobre as disciplinas e o valor total a ser cobrado.
3. O sistema de cobranças deve receber e processar a notificação sem erros.
4. O aluno deve receber uma confirmação de que o sistema de cobranças foi notificado.

## História 8: Validação de Ativação de Disciplinas
**Como** o sistema de matrículas,  
**eu quero** verificar se uma disciplina tem pelo menos 3 alunos matriculados ao final do período de matrículas,  
**para** determinar se a disciplina será ou não oferecida no próximo semestre.

### Critérios de Aceitação:
1. O sistema deve contar o número de alunos matriculados em cada disciplina ao final do período de matrículas.
2. A disciplina deve ser marcada como "ativa" se tiver 3 ou mais alunos matriculados e "cancelada" caso contrário.
3. O sistema deve gerar um relatório com a lista de disciplinas ativas e canceladas.
4. O sistema deve notificar o administrador sobre as disciplinas canceladas.

## História 9: Fechamento de Matrículas em Disciplinas Lotadas
**Como** o sistema de matrículas,  
**eu quero** encerrar as matrículas em uma disciplina assim que 60 alunos estiverem inscritos,  
**para** evitar sobrecarga nas turmas.

### Critérios de Aceitação:
1. O sistema deve monitorar o número de alunos matriculados em cada disciplina em tempo real.
2. As inscrições devem ser automaticamente encerradas quando a disciplina atingir o limite de 60 alunos.
3. O sistema deve exibir uma mensagem de erro se um aluno tentar se matricular em uma disciplina lotada.
4. O sistema deve atualizar a interface para refletir a disponibilidade da disciplina como "lotada".