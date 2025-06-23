# desafio-itau-backend

## Itaú Unibanco - Desafio de Programação

Este é um desafio bacana tanto de desenvolvimento de software quanto de engenharia de software. Queremos testar sua capacidade de construir um software com várias partes diferentes funcionando em conjunto!

Vídeo no youtube [https://youtu.be/uke3i4uOejs](https://youtu.be/uke3i4uOejs)

---

## 1. Introdução

Sua missão, caso você aceite, é criar uma API REST que recebe Transações e retorna Estatísticas sob essas transações.  
Para este desafio, a API deve ser criada utilizando-se de Java ou [Kotlin](https://kotlinlang.org/) e Spring Boot.

Um bom lugar para se começar é o [Spring Starter](https://start.spring.io/).

> 💡 Dica: Não existe uma forma certa ou errada de resolver o desafio! Vamos avaliar coisas como a qualidade do seu código, o quão fácil é de compreender o código, organização do projeto, quantidade e qualidade dos testes, preocupação com segurança e vários outros fatores :)

---

## 2. Definição do desafio

Neste desafio você deve **criar uma API REST** no [GitHub](https://github.com) ou [GitLab](https://gitlab.com). Leia com atenção todas as instruções a seguir!

---

### 2.1. Restrições Técnicas

Seu projeto:

- DEVE estar no GitHub ou GitLab  
- NÃO DEVE fazer fork de nenhum outro projeto  
- DEVE ter pelo menos 1 commit por cada endpoint (mínimo de 3 commits)  
  - Queremos ver a evolução do seu projeto com o tempo :)
- Todos os commits DEVEM ser feitos pelo mesmo usuário que criou o projeto  
- DEVE seguir exatamente os endpoints descritos a seguir  
  - Por exemplo, `/transacao` **não é a mesma coisa que** `/transacoes`
- DEVE aceitar e responder com objetos exatamente como descritos a seguir  
  - Por exemplo, `dataHora` **não é a mesma coisa que** `data-hora` ou `dtTransacao`
- NÃO DEVE utilizar quaisquer sistemas de banco de dados (como H2, MySQL, PostgreSQL, ...) ou cache (como Redis, Memcached, Infinispan, ...)
- DEVE armazenar todos os dados em **memória**
- DEVE aceitar e responder apenas com **JSON**

> ⚠️ Atenção! Por motivos de segurança, não podemos aceitar projetos enviados como arquivos. Você DEVE disponibilizar seu repositório publicamente para que possamos acessá-lo e corrigí-lo! Após receber uma resposta de nós, sinta-se livre para tornar seu projeto privado :)

---

### 2.2. Endpoints da API

A seguir serão especificados os endpoints que devem estar presentes na sua API e a funcionalidade esperada de cada um deles.

#### 2.2.1. Receber Transações: `POST /transacao`

Este é o endpoint que irá receber as Transações. Cada transação consiste de um **valor** e uma **dataHora** de quando ela aconteceu:

```json
{
  "valor": 123.45,
  "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

> Os campos no JSON acima significam o seguinte:

| Campo     | Significado                                           | Obrigatório |
|-----------|--------------------------------------------------------|-------------|
| `valor`   | Valor em decimal com ponto flutuante da transação     | Sim         |
| `dataHora`| Data/Hora no padrão ISO 8601 em que a transação ocorreu| Sim         |

> 💡 Dica: O Spring Boot, por padrão, consegue compreender datas no padrão ISO 8601 sem problemas. Experimente utilizar um atributo do tipo `OffsetDateTime`!

A API só aceitará transações que:

1. Tenham os campos `valor` e `dataHora` preenchidos  
2. A transação **NÃO DEVE** acontecer no futuro  
3. A transação **DEVE** ter acontecido a qualquer momento no passado  
4. A transação **NÃO DEVE** ter valor negativo  
5. A transação **DEVE** ter valor igual ou maior que 0 (zero)

Como resposta, espera-se que este endpoint responda com:

- `201 Created` sem nenhum corpo  
  - A transação foi aceita (foi validada, está válida e foi registrada)
- `422 Unprocessable Entity` sem nenhum corpo  
  - A transação não foi aceita por qualquer motivo (por exemplo: valor < 0)
- `400 Bad Request` sem nenhum corpo  
  - A API não compreendeu a requisição do cliente (exemplo: JSON inválido)

---

#### 2.2.2. Limpar Transações: `DELETE /transacao`

Este endpoint simplesmente **apaga todos os dados de transações** que estejam armazenadas.

Resposta esperada:

- `200 OK` sem nenhum corpo

---

#### 2.2.3. Calcular Estatísticas: `GET /estatistica`

Este endpoint deve retornar estatísticas das transações que **aconteceram nos últimos 60 segundos**:

```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
```

| Campo   | Significado                                        | Obrigatório |
|---------|----------------------------------------------------|-------------|
| `count` | Quantidade de transações nos últimos 60 segundos   | Sim         |
| `sum`   | Soma total do valor transacionado nos últimos 60s  | Sim         |
| `avg`   | Média do valor transacionado nos últimos 60s       | Sim         |
| `min`   | Menor valor transacionado nos últimos 60s          | Sim         |
| `max`   | Maior valor transacionado nos últimos 60s          | Sim         |

> 💡 Dica: Há um objeto no Java 8+ chamado `DoubleSummaryStatistics` que pode servir de inspiração.

Resposta esperada:

- `200 OK` com os dados das estatísticas

---

## 4. Extras

Vamos propor a seguir alguns desafios extras caso você queira testar seus conhecimentos ao máximo!

1. **Testes automatizados**  
2. **Containerização** (ex: Docker)  
3. **Logs**  
4. **Observabilidade** (ex: healthcheck endpoint)  
5. **Performance** (ex: estimar tempo de resposta)  
6. **Tratamento de Erros** personalizados  
7. **Documentação da API** (Swagger, etc.)  
8. **Documentação do Sistema** (README claro para execução)  
9. **Configurações** personalizáveis (ex: tempo da janela de estatísticas)
