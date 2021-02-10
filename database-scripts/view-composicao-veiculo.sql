CREATE VIEW VWCOMPVEIC AS
SELECT ISNULL(CAV.PLACA,'AAA0000') AS PLACACAVALO,
       ISNULL(CAV.ANTT,'') AS RNTRCAVALO,
       ISNULL(CAR1.PLACA,'AAA0000') AS PLACACARRETA1,
       ISNULL(CAR1.ANTT,'') AS RNTRCCARRETA1,
       ISNULL(CAR2.PLACA,'AAA0000') AS PLACACARRETA2,
       ISNULL(CAR2.ANTT,'') AS RNTRCCARRETA2,
       ISNULL(CAR3.PLACA,'AAA0000') AS PLACACARRETA3,
       ISNULL(CAR3.ANTT,'') AS RNTRCCARRETA3,
       ORD.ORDEMCARGA,
       ORD.CODEMP
FROM TGFORD ORD
         INNER JOIN TGFVEI CAV ON CAV.CODVEICULO = ORD.CODVEICULO
         LEFT JOIN TGFVEI CAR1 ON CAR1.CODVEICULO = ORD.CODVEI1
         LEFT JOIN TGFVEI CAR2 ON CAR2.CODVEICULO = ORD.CODVEI2
         LEFT JOIN TGFVEI CAR3 ON CAR3.CODVEICULO = ORD.CODVEI3