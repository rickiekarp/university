	.file	"myst.c"
	.text
	.globl	myst
	.type	myst, @function
myst:
.LFB0:
	.cfi_startproc
	pushl	%ebx
	.cfi_def_cfa_offset 8
	.cfi_offset 3, -8
	subl	$24, %esp
	.cfi_def_cfa_offset 32
	movl	32(%esp), %ebx
	movl	36(%esp), %edx
	movl	$1, %eax
	testl	%edx, %edx
	je	.L2
	subl	$1, %edx
	movl	%edx, 4(%esp)
	movl	%ebx, (%esp)
	call	myst
	imull	%ebx, %eax
.L2:
	addl	$24, %esp
	.cfi_def_cfa_offset 8
	popl	%ebx
	.cfi_def_cfa_offset 4
	.cfi_restore 3
	ret
	.cfi_endproc
.LFE0:
	.size	myst, .-myst
	.ident	"GCC: (Ubuntu/Linaro 4.6.3-1ubuntu5) 4.6.3"
	.section	.note.GNU-stack,"",@progbits
