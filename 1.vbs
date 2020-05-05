Dim ado, inputEncoding, outputEncoding, outFile, inputFile, fso, current_path, file, file2, inputText, outputText
Set ado = CreateObject("ADODB.Stream")
inputEncoding = "windows-1251"
outputEncoding = "koi8-r"
outFile = "output.txt"
inputFile = "input.txt"
Set fso = WScript.CreateObject("Scripting.FileSystemObject")
current_path = fso.getParentFolderName(WScript.ScriptFullName)
Set file = fso.createTextFile(current_path + "/" + inputFile, true)
file.writeline("Случайный текст")
file.close()
inputText = fso.openTextFile(current_path + "/" + inputFile).readAll()
ado.open
ado.charset = inputEncoding
ado.writeText(inputText)
ado.position = 0
ado.charset = outputEncoding
outputText = ado.readText()
ado.close
Set file2 = fso.createTextFile(current_path +  "/" + outFile, true)
file2.writeline(outputText)
file2.close