<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns="http://www.w3.org/1999/xhtml">

	<xsl:output method="text"
				doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
				doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
				encoding="UTF-8"
				indent="yes" />

	<xsl:variable name="lang" select="/cv/@lang"/>
	<xsl:variable name="translation" select="document('xml/translation.xml')/translation"/>

	<xsl:template match="/cv">
		<xsl:text>\documentclass[12pt]{article}&#xa;</xsl:text>
		<xsl:text>\usepackage[utf8]{inputenc}&#xa;</xsl:text>
		<xsl:text>\usepackage[LabelsAligned,NoDate]{currvita}&#xa;</xsl:text>
		<xsl:text>\usepackage[margin=0.75in]{geometry}&#xa;</xsl:text>
		<xsl:text>\usepackage{tikz}&#xa;</xsl:text>
		<xsl:text>\usepackage{xcolor}&#xa;</xsl:text>

		<xsl:text>\begin{document}&#xa;</xsl:text>
		<xsl:text>\renewcommand*{\cvheadingfont}{\huge\bfseries}&#xa;</xsl:text>
		<xsl:text>\renewcommand*{\cvlistheadingfont}{\large\bfseries}&#xa;</xsl:text>
		<xsl:text>\setlength{\cvlabelwidth}{41mm}&#xa;</xsl:text>

		<xsl:for-each select="personalInfo">
			<xsl:for-each select="name">
				<xsl:text>&#xa;	\begin{cv}{</xsl:text>
				<xsl:if test="count(title[@position='before'] | title[not(@*)]) != 0">
					<xsl:value-of select="title[@position='before'] | title[not(@*)]"/>
				</xsl:if>
				<xsl:text> </xsl:text>
				<xsl:value-of select="first"/>
				<xsl:text> </xsl:text>
				<xsl:value-of select="last"/>

				<xsl:if test="count(title[@position='after']) != 0">
					<xsl:text>, </xsl:text>
					<xsl:value-of select="title[@position='after']"/>
				</xsl:if>
				<xsl:text>}</xsl:text>
			</xsl:for-each>

			<!-- adresa -->
			<xsl:text>&#xa;		\begin{cvlist}{}</xsl:text>
			<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='address']/tr[@lang=$lang]"/>
			<xsl:text>:] </xsl:text>
			<xsl:value-of select="address/street"/>
			<xsl:text>&#xa;			\item[]</xsl:text>
			<xsl:value-of select="address/city"/>
			<xsl:text> , </xsl:text>
			<xsl:value-of select="address/country"/>
			<xsl:text>&#xa;			\item[] </xsl:text>
			<xsl:value-of select="address/zip"/>
			<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='birthday']/tr[@lang=$lang]"/>
			<xsl:text>:] </xsl:text>
			<xsl:value-of select="birthday/day"/>.<xsl:value-of select="birthday/month"/>.<xsl:value-of select="birthday/year"/>
			<xsl:for-each select="contact[type]">
				<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
				<xsl:value-of select="./@type"/>
				<xsl:text>:] </xsl:text>
				<xsl:value-of select="."/>
			</xsl:for-each>
			<xsl:text>&#xa;		\end{cvlist}</xsl:text>
		</xsl:for-each>

		<xsl:for-each select="education">
			<xsl:text>&#xa;&#xa;		\begin{cvlist}</xsl:text>
			<xsl:text>&#xa;		{</xsl:text>
			<xsl:text>&#xa;			\tikz[]</xsl:text>
			<xsl:text>&#xa;			{</xsl:text>
			<xsl:text>&#xa;				\node[shape=rectangle, text width=4in, fill=red!26!green!39!blue!26, text=black, minimum width=4in]</xsl:text>
			<xsl:text>&#xa;				{</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='education']/tr[@lang=$lang]"/>
			<xsl:text>};</xsl:text>
			<xsl:text>&#xa;			}</xsl:text>
			<xsl:text>&#xa;		}</xsl:text>
			<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='highest_education']/tr[@lang=$lang]"/>
			<xsl:text>:]</xsl:text>																		<!-- TODO preklad -->
			<xsl:variable name="highestEducation" select="./@highest"/>
			<xsl:value-of select="$translation/expression[@exp=$highestEducation]/tr[@lang=$lang]"/>
			<xsl:for-each select="school">
				<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
				<xsl:if test="count(start/day) = 1">
					<xsl:value-of select="start/day"/>
					<xsl:text>.</xsl:text>
					<xsl:if test="count(start/month) = 1">
						<xsl:value-of select="start/month"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
				</xsl:if>
				<xsl:value-of select="start/year"/>
				<xsl:text>-</xsl:text>
				<xsl:if test="count(end/day) = 1">
					<xsl:value-of select="end/day"/>
					<xsl:text>.</xsl:text>
					<xsl:if test="count(end/month) = 1">
						<xsl:value-of select="end/month"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
				</xsl:if>
				<xsl:value-of select="end/year"/>
				<xsl:text>] </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>&#xa;			\item[] </xsl:text>
				<xsl:value-of select="$translation/expression[@exp='specialization']/tr[@lang=$lang]"/>
				<xsl:text>: </xsl:text>
				<xsl:value-of select="specialization"/>
			</xsl:for-each>

			<!--<xsl:text>\item[ ] test</xsl:text>-->
			<xsl:text>&#xa;		\end{cvlist}</xsl:text>
		</xsl:for-each>

		<xsl:if test="count(work) != 0">
			<xsl:text>&#xa;&#xa;		\begin{cvlist}</xsl:text>
			<xsl:text>&#xa;		{</xsl:text>
			<xsl:text>&#xa;			\tikz[]</xsl:text>
			<xsl:text>&#xa;			{</xsl:text>
			<xsl:text>&#xa;				\node[shape=rectangle, text width=4in, fill=red!26!green!39!blue!26, text=black, minimum width=4in]</xsl:text>
			<xsl:text>&#xa;				{</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='work']/tr[@lang=$lang]"/>
			<xsl:text>};</xsl:text>
			<xsl:text>&#xa;			}</xsl:text>
			<xsl:text>&#xa;		}</xsl:text>

			<xsl:for-each select="work">
				<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
				<xsl:if test="count(start/day) = 1">
					<xsl:value-of select="start/day"/>
					<xsl:text>.</xsl:text>
					<xsl:if test="count(start/month) = 1">
						<xsl:value-of select="start/month"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
				</xsl:if>
				<xsl:value-of select="start/year"/>
				<xsl:text>-</xsl:text>
				<xsl:if test="count(end/day) = 1">
					<xsl:value-of select="end/day"/>
					<xsl:text>.</xsl:text>
					<xsl:if test="count(end/month) = 1">
						<xsl:value-of select="end/month"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
				</xsl:if>
				<xsl:value-of select="end/year"/>
				<xsl:text>] </xsl:text>
				<xsl:value-of select="employer"/>
				<xsl:text>&#xa;			\item[] </xsl:text>
				<xsl:value-of select="$translation/expression[@exp='position']/tr[@lang=$lang]"/>
				<xsl:text>: </xsl:text>
				<xsl:value-of select="position"/>

			</xsl:for-each>
			<xsl:text>&#xa;		\end{cvlist}</xsl:text>
		</xsl:if>

		<xsl:if test="count(language) > 0">
			<xsl:text>&#xa;&#xa;		\begin{cvlist}</xsl:text>
			<xsl:text>&#xa;		{</xsl:text>
			<xsl:text>&#xa;			\tikz[]</xsl:text>
			<xsl:text>&#xa;			{</xsl:text>
			<xsl:text>&#xa;				\node[shape=rectangle, text width=4in, fill=red!26!green!39!blue!26, text=black, minimum width=4in]</xsl:text>
			<xsl:text>&#xa;				{</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='languages']/tr[@lang=$lang]"/>
			<xsl:text>};</xsl:text>
			<xsl:text>&#xa;			}</xsl:text>
			<xsl:text>&#xa;		}</xsl:text>

			<xsl:for-each select="language">
				<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
				<xsl:value-of select="."/>
				<xsl:text>] </xsl:text>
				<xsl:value-of select="./@level"/>
			</xsl:for-each>
			<xsl:text>&#xa;		\end{cvlist}</xsl:text>
		</xsl:if>

		<xsl:if test="count(skill) > 0">
			<xsl:text>&#xa;&#xa;		\begin{cvlist}</xsl:text>
			<xsl:text>&#xa;		{</xsl:text>
			<xsl:text>&#xa;			\tikz[]</xsl:text>
			<xsl:text>&#xa;			{</xsl:text>
			<xsl:text>&#xa;				\node[shape=rectangle, text width=4in, fill=red!26!green!39!blue!26, text=black, minimum width=4in]</xsl:text>
			<xsl:text>&#xa;				{</xsl:text>
			<xsl:value-of select="$translation/expression[@exp='skills']/tr[@lang=$lang]"/>
			<xsl:text>};</xsl:text>
			<xsl:text>&#xa;			}</xsl:text>
			<xsl:text>&#xa;		}</xsl:text>

			<xsl:for-each select="skill">
				<xsl:text>&#xa;			\item[\hspace{3mm}</xsl:text>
				<xsl:value-of select="."/>
				<xsl:text>] </xsl:text>
				<xsl:value-of select="./@level"/>
			</xsl:for-each>
			<xsl:text>&#xa;		\end{cvlist}</xsl:text>
		</xsl:if>


		<xsl:text>&#xa;	\end{cv}</xsl:text>
		<xsl:text>&#xa;\end{document}</xsl:text>
	</xsl:template>



</xsl:stylesheet>