Section recuacional.

(* Definicion de booleano *)
Inductive booleano: Type :=
|btrue: booleano
|bfalse: booleano.

(* Definicion and*)
Definition and (b1 b2: booleano) : booleano :=
match b1 with
|bfalse => bfalse
|_ => b2
end.

(* Definicion or*)
Definition or (b1 b2: booleano) : booleano :=
match b1 with
|btrue => btrue
|_ => b2
end.

Definition impl ( b1 b2 : booleano ) : booleano :=
match b1 with
| bfalse => btrue
| _ => b2
end .

(* Definicion negacion*)
Definition neg (b1: booleano) : booleano :=
match b1 with
|btrue => bfalse
|bfalse => btrue
end.

(* PARTE I *)

(* Reglas de conjuncion *)
Theorem andI: forall (p q :booleano), p = btrue -> q = btrue -> and p q = btrue.
intros.
rewrite H.
simpl.
trivial.
Qed.

Theorem andE1: forall (p q :booleano), and p q = btrue -> p = btrue.
intros.
induction p.
reflexivity.
discriminate.
Qed.
Theorem comAnd: forall (p q : booleano) , and p q = and q p.
intros.
induction p.
induction q.
simpl.
reflexivity.
simpl.
reflexivity.
induction q.
simpl.
reflexivity.
simpl.
reflexivity.
Qed.
 
Theorem andE2: forall (p q :booleano), and p q = btrue -> q = btrue.
intros.
simpl.
rewrite comAnd in H.
induction q.
reflexivity.
discriminate.
Qed.


(* Regla de la doble negacion *)
Theorem dobleNeg: forall (p :booleano), neg(neg(p)) = p.
intros.
induction p.
simpl.
reflexivity.
simpl.
reflexivity.
Qed.

(* Eliminacion de la implicacion *)
Theorem elimImpl:forall(f g:booleano), f=btrue -> impl f g=btrue -> g=btrue.
intros.
rewrite H in H0.
induction g.
reflexivity.
discriminate.
Qed.

(* Modus Tollens *)
Theorem modusTollens: forall(f g :booleano), impl f g = btrue -> neg g = btrue 
-> neg f = btrue.
intros.
assert (neg (neg g)=bfalse) as H1.
rewrite H0.
simpl.
reflexivity.
rewrite dobleNeg in H1.
rewrite H1 in H.
induction f.
discriminate.
simpl.
reflexivity.
Qed. 

(* Introduccion de la implicacion *)
Theorem introImpl: forall (p q :booleano), p = btrue -> q = btrue 
-> impl p q = btrue.
intros.
rewrite H.
rewrite H0.
simpl.
reflexivity.
Qed.
(* PARTE II *)

(* Probar los siguientes ejemplos: solo está permitido usar las tácticas apply, 
intros, rewrite, exact y trivial. Además, si se escriben lemas auxiliares, 
se deben usar exclusivamente estas tácticas para probarlos. *)

Example ejemplo1: forall(p q r :booleano), and p q = btrue -> r = btrue 
-> and q r = btrue.
intros.
apply andE2 in H.
rewrite H0.
rewrite H.
simpl.
trivial.
Qed.

Example ejemplo2: forall (p q r :booleano), p = btrue -> neg(neg(and q r)) = btrue 
-> and (neg(neg p)) r = btrue.
intros.
rewrite dobleNeg.
rewrite dobleNeg in H0.
apply andE2 in H0.
rewrite H.
rewrite H0.
simpl.
reflexivity.
Qed.

Example ejemplo3: forall (p q r: booleano), and (neg p) q = btrue 
-> impl (and (neg p) q) (or r (neg p)) = btrue -> or r(neg p) = btrue.
intros.
apply elimImpl in H0.
trivial.
trivial.
Qed.


Example ejemplo4: forall (p q r :booleano), impl p (impl q r) = btrue 
-> p = btrue -> neg r = btrue -> neg q= btrue.
intros.
apply elimImpl in H.
apply modusTollens in H.
trivial.
trivial.
trivial.
Qed.

(* PARTE III *)
Variable A:Type.
Variable x:A.

(* Eliminacion de Para todo *)
Theorem elimForall: forall (p:A->Prop), (forall y:A, p y) -> p x.
intros.
apply H.
Qed.


Theorem introExist: forall (p :A->Prop), p x-> exists (y:A), p y.
intros.
exists x.
apply H.
Qed.

(* PARTE IV *)

(* Demostrar los siguientes ejemplos usando solo las tácticas intros, 
apply, trivial, exists, destruct y exact.*)
 
Example  ejemplo5:forall (p q :A->Prop)  , (forall (y:A), p y -> q y)  
-> (forall (y:A), p y) -> (forall (y:A), q y).
intros.
apply H.
apply H0.
Qed.

Example ejemplo6: forall (p:A->Prop), (forall (y:A), p y) -> (exists (y:A), p y).
intros.
apply introExist.
apply H.
Qed.

Example ejemplo7: forall (p q :A->Prop), (forall (y:A), p y -> q y) 
-> (exists (y:A), p y) -> (exists (y:A), q y).
intros.
destruct H0.
exists x0.
apply H.
apply H0.
Qed.


End recuacional.

