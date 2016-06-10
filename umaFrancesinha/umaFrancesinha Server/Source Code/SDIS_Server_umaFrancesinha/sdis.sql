	

    DROP TABLE Utilizador;
    DROP TABLE Notificacao;
    DROP TABLE Restaurante;
    DROP TABLE Atualizacoes;
    DROP TABLE Avaliacao;
     
    CREATE TABLE Utilizador
    (
                    id INTEGER NOT NULL,
            nome varchar2(50) NOT NULL,
            username varchar2(15) UNIQUE,
            pw varchar2(20) NOT NULL,
            ranking REAL,
            CONSTRAINT utilizador_pk PRIMARY KEY (id)
    );
     
    CREATE TABLE Restaurante
    (       id INTEGER NOT NULL,
            nome varchar2(50) NOT NULL,
            username varchar2(50),
            pw varchar2(20) NOT NULL,
            rua VARCHAR(20) NOT NULL,
            cidade VARCHAR(20) NOT NULL,
            fotografia VARCHAR(100),
            ranking REAL,
            preco REAL NOT NULL,  
            contacto VARCHAR(20),
            CONSTRAINT restaurante_pk PRIMARY KEY (id)
    );
     
    CREATE TABLE Atualizacoes
    (       id INTEGER NOT NULL,
            id_restaurante REFERENCES Restaurante(id) NOT NULL,
            nome varchar2(50) NOT NULL,
            descricao varchar2(20) NOT NULL,
            data_atualizacao DATE NOT NULL,
            data_inicio DATE NOT NULL,
            data_fim DATE NOT NULL,
            CONSTRAINT actualizacoes_pk PRIMARY KEY (id)
    );
     
    CREATE TABLE Avaliacao
    (       id INTEGER NOT NULL,
            id_restaurante REFERENCES Restaurante(id) NOT NULL,
            id_utilizador REFERENCES Utilizador(id) NOT NULL,
            pontuacao INTEGER NOT NULL,
            foto VARCHAR(200) NOT NULL,
            comentario VARCHAR(100),
            data_avaliacao text NOT NULL,
			hms text NOT NULL,
            CONSTRAINT avaliacao_pk PRIMARY KEY (id)
    );
     
    CREATE TABLE Notificacao
    (	id_notificacao INTEGER NOT NULL,
		id_restaurante REFERENCES Restaurante(id) NOT NULL,
        id_utilizador REFERENCES Utilizador(id) NOT NULL,
		visto INTEGER,
		descricao_notificacao VARCHAR(200),
        CONSTRAINT Notificacao_pk PRIMARY KEY (id_notificacao)
    );
     
     
    CREATE TRIGGER novaAvaliacao AFTER INSERT
    ON Avaliacao
    BEGIN
       UPDATE Restaurante SET ranking = (SELECT avg(pontuacao) FROM Avaliacao WHERE id_restaurante= NEW.id_restaurante GROUP BY id_restaurante) WHERE Restaurante.id = NEW.id_restaurante ;
       UPDATE Utilizador SET ranking = ranking + ( (NEW.pontuacao - (SELECT avg(pontuacao) FROM Avaliacao WHERE id_restaurante = NEW.id_restaurante GROUP BY id_restaurante)) * (NEW.pontuacao - (SELECT avg(pontuacao) FROM Avaliacao WHERE id_restaurante = NEW.id_restaurante GROUP BY id_restaurante)) )  WHERE Utilizador.id = NEW.id_utilizador;
    END;
     
     
    INSERT INTO Restaurante VALUES(NULL,'Os Compadres','compadres','12345','Rua Justino Teixeira, 509','Porto','kn.png',0.0, 6.0, '225377794');
    INSERT INTO Restaurante VALUES(NULL,'Capa Negra II','capanegra','12345','Rua de Campo Alegre','Porto','kn2.png',0.0, 11.0, '226078380');
    INSERT INTO Restaurante VALUES(NULL,'Tappas Café','tappascafe','12345','Rua Doutor António Granjo 549 ','Vila Nova de Gaia','kn2.png',0.0, 8.0, '223706196');
    INSERT INTO Restaurante VALUES(NULL,'Regaleira','regaleira','12345','Rua do Bonjardim, 87 ','Porto','kn2.png',0.0, 8.0, '222006465');
    INSERT INTO Utilizador VALUES(NULL,'Paulo Marcos','paulosuperd','12345',0.0);
    INSERT INTO Utilizador VALUES(NULL,'Joao Trindade','joaotrindade','12345',0.0);
    INSERT INTO Utilizador VALUES(NULL,'Pedro Santos','pedrosantos','12345',0.0);
    INSERT INTO Utilizador VALUES(NULL,'Rui Neves','ruineves','12345',0.0);
    INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,2,1,5,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
    INSERT INTO Avaliacao VALUES(NULL,3,1,4,'null.png','MUITO BOOMII','29-05-2014','10:04:20:12');
    INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
	INSERT INTO Avaliacao VALUES(NULL,1,2,5,'null.png','Caseiras! As melhores!','31-05-2014','17:01:06:13');
    INSERT INTO Avaliacao VALUES(NULL,4,2,4,'null.png','Onde a francesinha nasceu!','31-05-2014','18:43:06:14');
    INSERT INTO Avaliacao VALUES(NULL,3,3,4,'null.png','As melhores de Gaia!','31-05-2014','21:24:06:15');
    INSERT INTO Avaliacao VALUES(NULL,1,1,5,'null.png','Concordo com o Trindade :D','31-05-2014','11:20:45:16');
	INSERT INTO Avaliacao VALUES(NULL,2,4,1,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Avaliacao VALUES(NULL,1,4,1,'null.png','MUITO BOOM','29-05-2014','20:04:06:11');
	INSERT INTO Notificacao VALUES(NULL,1,1,0,'Descontos!');
	INSERT INTO Notificacao VALUES(NULL,2,1,0,'Saldos!');
	INSERT INTO Notificacao VALUES(NULL,3,1,0,'Oferta de Batatas Fritas!');
	INSERT INTO Notificacao VALUES(NULL,1,2,0,'Descontos!');
	INSERT INTO Notificacao VALUES(NULL,2,2,0,'Saldos!');
	INSERT INTO Notificacao VALUES(NULL,3,2,0,'Oferta de Batatas Fritas!');
	INSERT INTO Notificacao VALUES(NULL,4,2,0,'Cerveja à borla!');

