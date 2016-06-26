#!/usr/local/GNU/bin/perl

# Usage: lookup.perl <file>*
# Perl script to be used prior to tagging

# It assigns sets of possible tags to selected word forms.
# A file named "elex" containing these word forms and their tags
# has to be in the current working directory.
# The format of this file is:
# <word form><tab>[<tag><whitespace>{<tag prob.><whitespace>}]*
# The word form which may contain blanks is followed by a tab character
# and a sequence of tags separated by whitespace. The tags are optionally
# followed by tag probability values in the range from 0.0 to 1.0.

$LEXICON = "elex";

open(LEXICON);
while (<LEXICON>) {
    chop();
    @word = split('\t');
    $word[1] =~ s/[ \t\n][ \t\n]*/ /go;
    $word[1] =~ s/^[ \t\n]*(.*[^ \t\n])[ \t\n]*$/$1/go;
    $tag{$word[0]} = $word[1];
}
close(INPUT);

while (<>) { 
    chop();
    s/[ \t\n][ \t\n]*/ /go;
    s/^[ \t\n]*(.*[^ \t\n])[ \t\n]*$/$1/go;
    if (defined($tag{$_})) {
	print $_,"\t",$tag{$_},"\n";
    }
    else {
	print $_, "\n";
    }
}
