ALTER
VIEW WVDADOSOPTRANS AS
SELECT CASE
           WHEN CAB.CODPARC <> CAB.CODPARCREMETENTE
               THEN 2
           ELSE 1 END                                                                                AS PROPCARGA,
       (SELECT SUM(PESOTOTAL) FROM VMSCTE WHERE ORDEMCARGA = ORD.ORDEMCARGA AND CODEMP = ORD.CODEMP) AS PESOCARGA,
       ISNULL(ORIG.CODMUNFIS, 0)                                                                     AS CODIBGEORIG,
       ISNULL(DEST.CODMUNFIS, 0)                                                                     AS CODIBGEDEST,
       ISNULL(FORMAT(GETDATE() + 1, 'yyyy-MM-dd'), '')                                               AS DTINIC,
       ISNULL(FORMAT(ORD.DHPREVCGD, 'yyyy-MM-dd'), '')                                               AS DTFIM,
       ISNULL(AFT.VLRFRTACERT, 0)                                                                    AS VLRFRETE,
       ISNULL(ORD.VLRPEDAGIOTOT, 0)                                                                  AS VLRPEDAGIO,
       ISNULL(AFT.VLRSESTSENAT, 0)                                                                   AS VLRSESTSENAT,
       ISNULL(AFT.VLRIRRF, 0)                                                                        AS VLRIRRF,
       ISNULL(AFT.VLRINSS, 0)                                                                        AS VLRINSS,
       CASE
           WHEN ISNULL(AFT.VLRADIANT, 0) = 0 OR ISNULL(AFT.VLRSALDOFRETE, 0) = 0
               THEN 'S'
           ELSE 'N' END                                                                              AS PARCELAUNICA,
       CASE
           WHEN AFT.FORMAPGTOPEDAGIO = 'TG' THEN 1
           WHEN AFT.FORMAPGTOPEDAGIO = 'S' THEN 2
           ELSE 4 END                                                                                AS FORMAPGTOPEDAGIO,
       ISNULL(CMP.CODCATEGTARGET, 0)                                                                 AS CATEGVEIC,
       ISNULL(ORD.CODROTA, 0)                                                                        AS ROTA,
       ISNULL(PARANTT.TIPPESSOA, '')                                                                 AS TIPPESSOA,
       ISNULL('OC.: ' + CAST(ORD.ORDEMCARGA AS VARCHAR) + ' - EMP.: ' + CAST(ORD.CODEMP AS VARCHAR) +
              ' - AFT.: ' + CAST(AFT.CODAFT AS VARCHAR), '')                                         AS IDINTEGRADOR,
       ISNULL(AFT.NROCARTAOPEDAGIO, 0)                                                               AS CARTAOPEDAGIO,
       CASE
           WHEN ORD.CODPARCCOL IS NOT NULL
               THEN (SELECT CEP
                     FROM TGFPAR
                     WHERE CODPARC = ORD.CODPARCCOL)
           ELSE (SELECT CEP
                 FROM TGFPAR
                 WHERE CODPARC = ORD.CODPARCREM) END                                                 AS CEPORIGEM,
       CASE
           WHEN ORD.CODPARCENT IS NOT NULL
               THEN (SELECT CEP
                     FROM TGFPAR
                     WHERE CODPARC = ORD.CODPARCENT)
           ELSE (SELECT CEP
                 FROM TGFPAR
                 WHERE CODPARC = ORD.CODPARCDEST) END                                                AS CEPDESTINO,
       ISNULL(ORD.AD_TIPCARGAANTT, 0)                                                                AS TIPCARGAANTT,
       ISNULL(AFT.VLRTAXASEGURO, 0)                                                                  AS VLRTAXASEGURO,
       ORD.ORDEMCARGA,
       ORD.CODEMP,
       AFT.CODAFT
FROM TGFCAB CAB
         INNER JOIN TGFORD ORD ON ORD.ORDEMCARGA = CAB.ORDEMCARGA AND ORD.CODEMP = CAB.CODEMP
         INNER JOIN VMSCTE CTE ON CTE.ORDEMCARGA = ORD.ORDEMCARGA AND CTE.CODEMP = ORD.CODEMP AND CTE.CODPARCDEST = CAB.CODPARCDEST
         INNER JOIN TGFROT ROT ON ROT.CODROTA = ORD.CODROTA
         INNER JOIN TSICID ORIG ON ORIG.CODCID = ROT.CODCIDORIG
         INNER JOIN TSICID DEST ON DEST.CODCID = ROT.CODCIDDEST
         INNER JOIN TGFPAR PAR ON PAR.CODPARC = (CASE
                                                     WHEN CAB.CODPARCREDESPACHO = 0
                                                         THEN CAB.CODPARCDEST
                                                     ELSE CAB.CODPARCREDESPACHO END)
         INNER JOIN TMSORDAFT AFT ON AFT.ORDEMCARGA = ORD.ORDEMCARGA AND AFT.CODEMP = ORD.CODEMP
         INNER JOIN TMSCMP CMP ON CMP.CODVEICULO = ORD.CODVEICULO
         INNER JOIN TGFVEI VEI ON VEI.CODVEICULO = ORD.CODVEICULO
         INNER JOIN TGFPAR PARANTT ON PARANTT.CODPARC = VEI.CODPARCPROPANTT